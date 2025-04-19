package org.apache.commons.compress.archivers.zip;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)  // 添加运行器
@Suite
@SelectClasses({TestZipArchiveEntryJunit.class})
@IncludeTags("test_Entry")
public class AllTests {

}
