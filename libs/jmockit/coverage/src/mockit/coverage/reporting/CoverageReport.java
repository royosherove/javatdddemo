/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage.reporting;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import mockit.coverage.data.*;
import mockit.coverage.reporting.packages.*;
import mockit.coverage.reporting.sourceFiles.*;

class CoverageReport
{
   private final String outputDir;
   private final List<File> sourceDirs;
   private final Map<String, FileCoverageData> fileToFileData;
   private final Map<String, List<String>> packageToFiles;
   private final boolean withCallPoints;
   private final Collection<String> sourceFilesNotFound;

   protected CoverageReport(String outputDir, String[] srcDirs, CoverageData coverageData, boolean withCallPoints)
   {
      this.outputDir = outputDir.length() > 0 ? outputDir : "coverage-report";
      sourceDirs = srcDirs == null ? null : new SourceFiles().buildListOfSourceDirectories(srcDirs);
      fileToFileData = coverageData.getFileToFileDataMap();
      packageToFiles = new HashMap<String, List<String>>();
      this.withCallPoints = withCallPoints;
      sourceFilesNotFound = srcDirs == null ? null : new ArrayList<String>();
   }

   public final void generate() throws IOException
   {
      createReportOutputDirIfNotExists();

      File outputFile = createOutputFileForIndexPage();

      if (outputFile == null) {
         return;
      }

      boolean withSourceFilePages = sourceDirs != null;

      if (withSourceFilePages && sourceDirs.size() > 1) {
         System.out.println("JMockit: Coverage source dirs: " + sourceDirs);
      }

      generateFileCoverageReportsWhileBuildingPackageLists();

      new IndexPage(outputFile, sourceDirs, sourceFilesNotFound, packageToFiles, fileToFileData).generate();
      new StaticFiles(outputDir).copyToOutputDir(withSourceFilePages);

      System.out.println("JMockit: Coverage report written to " + outputFile.getParentFile().getCanonicalPath());
   }

   private void createReportOutputDirIfNotExists()
   {
      File outDir = new File(outputDir);

      if (!outDir.exists()) {
         boolean dirCreated = outDir.mkdir();
         assert dirCreated : "Failed to create output dir: " + outputDir;
      }
   }

   private File createOutputFileForIndexPage() throws IOException
   {
      File outputFile = new File(outputDir, "index.html");

      if (outputFile.exists() && !outputFile.canWrite()) {
         System.out.println("JMockit: " + outputFile.getCanonicalPath() + " is read-only; report generation canceled");
         return null;
      }

      return outputFile;
   }

   private void generateFileCoverageReportsWhileBuildingPackageLists() throws IOException
   {
      Set<Entry<String, FileCoverageData>> files = fileToFileData.entrySet();

      for (Entry<String, FileCoverageData> fileAndFileData : files) {
         generateFileCoverageReport(fileAndFileData.getKey(), fileAndFileData.getValue());
      }
   }

   private void generateFileCoverageReport(String sourceFile, FileCoverageData fileData) throws IOException
   {
      if (sourceDirs == null) {
         addFileToPackageFileList(sourceFile);
      }
      else {
         InputFile inputFile = new InputFile(sourceDirs, sourceFile);

         if (inputFile.wasFileFound()) {
            new FileCoverageReport(outputDir, inputFile, fileData, withCallPoints).generate();
         }
         else {
            sourceFilesNotFound.add(sourceFile);
         }

         addFileToPackageFileList(sourceFile);
      }
   }

   private void addFileToPackageFileList(String file)
   {
      int p = file.lastIndexOf('/');
      String filePackage = p < 0 ? "" : file.substring(0, p);
      List<String> filesInPackage = packageToFiles.get(filePackage);

      if (filesInPackage == null) {
         filesInPackage = new ArrayList<String>();
         packageToFiles.put(filePackage, filesInPackage);
      }

      filesInPackage.add(file.substring(p + 1));
   }
}
