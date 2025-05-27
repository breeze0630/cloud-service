package com.cloud

import org.gradle.api.Plugin
import org.gradle.api.Project


class BaseProjectPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // 设置项目基本信息
        project.group = 'com.cloud.service'
        project.version = '1.0.0'

        // 配置仓库
        project.repositories {
            mavenCentral()
        }
    }
 }