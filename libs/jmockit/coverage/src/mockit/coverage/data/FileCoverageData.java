/*
 * Copyright (c) 2006-2013 Rogério Liesenfeld
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.coverage.data;

import java.io.*;
import java.util.*;

import mockit.coverage.*;
import mockit.coverage.dataItems.*;
import mockit.coverage.lines.*;
import mockit.coverage.paths.*;

/**
 * Coverage data gathered for the lines and branches of a single source file.
 */
public final class FileCoverageData implements Serializable
{
   private static final long serialVersionUID = 3508572808457541012L;

   public final PerFileLineCoverage lineCoverageInfo = new PerFileLineCoverage();
   public final PerFilePathCoverage pathCoverageInfo = new PerFilePathCoverage();
   public final PerFileDataCoverage dataCoverageInfo = new PerFileDataCoverage();

   // Used for output styling in the HTML report:
   public String kindOfTopLevelType;

   // Used to track the last time the ".class" file was modified, to decide if merging can be done:
   long lastModified;

   public FileCoverageData(String kindOfTopLevelType) { this.kindOfTopLevelType = kindOfTopLevelType; }

   public LineCoverageData addLine(int line) { return lineCoverageInfo.addLine(line); }
   public SortedMap<Integer, LineCoverageData> getLineToLineData() { return lineCoverageInfo.lineToLineData; }

   public void addMethod(MethodCoverageData methodData) { pathCoverageInfo.addMethod(methodData); }
   public Collection<MethodCoverageData> getMethods() { return pathCoverageInfo.firstLineToMethodData.values(); }

   public PerFileCoverage getPerFileCoverage(Metrics metric)
   {
      switch (metric) {
         case LineCoverage: return lineCoverageInfo;
         case PathCoverage: return pathCoverageInfo;
         default: return dataCoverageInfo;
      }
   }

   void mergeWithDataFromPreviousTestRun(FileCoverageData previousInfo)
   {
      lineCoverageInfo.mergeInformation(previousInfo.lineCoverageInfo);
      pathCoverageInfo.mergeInformation(previousInfo.pathCoverageInfo);
      dataCoverageInfo.mergeInformation(previousInfo.dataCoverageInfo);
   }

   void reset()
   {
      lineCoverageInfo.reset();
      pathCoverageInfo.reset();
   }
}
