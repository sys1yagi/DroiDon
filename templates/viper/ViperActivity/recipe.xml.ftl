<?xml version="1.0"?>
<recipe>

    <instantiate from="src/app_package/ViperActivity.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Activity.kt"/>
    <instantiate from="src/app_package/ViperActivityComponent.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}ActivityComponent.kt"/>
    <instantiate from="res/layout/activity_viper.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/activity${className?replace("[A-Z]", "_$0", "r")?lower_case}.xml" />
    <instantiate from="src/app_package/ViperActivityModule.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}ActivityModule.kt"/>
    <instantiate from="src/app_package/ViperActivityObjectModule.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}ActivityObjectModule.kt"/>

    <#include "../ViperContract/recipe_contract.xml.ftl" />

    <open file="${srcOut}/${className}Activity.kt"/>
    <open file="${resOut}/layout/activity${className?replace("[A-Z]", "_$0", "r")?lower_case}.xml"/>
</recipe>
