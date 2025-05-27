import org.gradle.api.Plugin
import org.gradle.api.Project

class JavaProjectPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // 应用基础插件
        project.apply plugin: "com.cloud.plugin.base-project"

        // 应用 Java 插件
        project.apply plugin: 'java'

        project.version = '1.0.0'

        // 配置 Java 版本
        project.java {
            sourceCompatibility = "17"
            targetCompatibility = "17"
        }
//        def libs = project.extensions.getByType(VersionCatalogsExtension).named("libs")
//
//        // 配置依赖
//
//        project.dependencies {
//            implementation libs.findLibrary("spring-cloud").get()
//            implementation libs.findLibrary("lombox").get()
//
//        }

    }
}