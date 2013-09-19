/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage;

import java.lang.instrument.*;
import java.security.*;

import mockit.coverage.data.*;
import mockit.coverage.modification.*;
import mockit.coverage.standalone.*;

public final class CodeCoverage implements ClassFileTransformer
{
   private static CodeCoverage instance;

   private final ClassModification classModification;
   private final OutputFileGenerator outputGenerator;

   public static void main(String[] args)
   {
      OutputFileGenerator generator = createOutputFileGenerator(null);
      generator.generateAggregateReportFromInputFiles(args);
   }

   private static OutputFileGenerator createOutputFileGenerator(ClassModification classModification)
   {
      OutputFileGenerator generator = new OutputFileGenerator(classModification);
      CoverageData.instance().setWithCallPoints(generator.isWithCallPoints());
      return generator;
   }

   @SuppressWarnings("UnusedDeclaration")
   public CodeCoverage() { this(true); }

   private CodeCoverage(final boolean generateOutputOnShutdown)
   {
      if (generateOutputOnShutdown && "none".equals(System.getProperty("jmockit-coverage-output"))) {
         throw new IllegalStateException("JMockit: coverage tool disabled");
      }

      classModification = new ClassModification();
      outputGenerator = createOutputFileGenerator(classModification);

      Runtime.getRuntime().addShutdownHook(new Thread() {
         @Override
         public void run()
         {
            if (generateOutputOnShutdown) {
               if (outputGenerator.isOutputToBeGenerated()) {
                  outputGenerator.generate();
               }

               new CoverageCheck().verifyThresholds();
            }

            Startup.instrumentation().removeTransformer(CodeCoverage.this);
         }
      });
   }

   public static CodeCoverage create(boolean generateOutputOnShutdown)
   {
      instance = new CodeCoverage(generateOutputOnShutdown);
      return instance;
   }

   public static void resetConfiguration()
   {
      Startup.instrumentation().removeTransformer(instance);
      CoverageData.instance().clear();
      Startup.instrumentation().addTransformer(create(false));
   }

   public static void generateOutput(boolean resetState)
   {
      instance.outputGenerator.generate();

      if (resetState) {
         CoverageData.instance().reset();
      }
   }

   public byte[] transform(
      ClassLoader loader, String internalClassName, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
      byte[] originalClassfile)
   {
      if (loader == null || classBeingRedefined != null || protectionDomain == null) {
         return null;
      }

      String className = internalClassName.replace('/', '.');

      return classModification.modifyClass(className, protectionDomain, originalClassfile);
   }
}
