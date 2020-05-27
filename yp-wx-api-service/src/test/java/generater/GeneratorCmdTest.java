//package generater;
//
//import com.fd.businessengine.common.generator.GeneratorEntity;
//import com.fd.businessengine.common.generator.GeneratorService;
//import org.jetbrains.annotations.NotNull;
//import org.junit.Test;
//
//import java.io.File;
//
///**
// * 	实体类代码生成
// **/
//public class GeneratorCmdTest {
//
//    @Test
//    public void generate() {
//        String javaPath = "src/main/java";
//        String resPath = "src/main/resources";
//        String[] riskReportTables1 = {
//                "activity_award_rule",
//        };
//        String[] tablePrefix = {"fdft","trip"};
//
//        //生成实体
//        GeneratorEntity generatorEntity = new GeneratorEntity()
//                .setDbUrl("jdbc:mysql://47.113.116.88:3306/yiping?useSSL=false&serverTimezone=UTC")
//                .setDbUserName("yiping")
//                .setDbPassword("123456")
//                .setPackageName("com.yp")
//                .setJavaPath(new File(getProjectDir("yp-common"), javaPath).getPath());
//        generatorEntity.generateByTables("", riskReportTables1, tablePrefix);
//
//        ////生成seivice
//        GeneratorService generatorService = new GeneratorService()
//                .setDbUrl("jdbc:mysql://47.113.116.88:3306/yiping?useSSL=false&serverTimezone=UTC")
//                .setDbUserName("yiping")
//                .setDbPassword("123456")
//                .setPackageName("com.yp")
//                .setJavaPath(new File(getProjectDir("yp-common"), javaPath).getPath())
//                .setResPath(new File(getProjectDir("yp-common"), resPath).getPath());
//        generatorService.generateByTables("", riskReportTables1, tablePrefix);
//    }
//
//    @NotNull
//    public File getProjectDir(String projectName) {
//        return new File(new File("").getAbsoluteFile().getParentFile(), projectName);
//    }
//}
