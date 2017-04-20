<?xml version="1.0"?>
<recipe>

    <instantiate from="src/app_package/ViperFragment.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Fragment.kt"/>
    <instantiate from="src/app_package/ViperFragmentComponent.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}FragmentComponent.kt"/>
    <instantiate from="res/layout/fragment_viper.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/fragment${className?replace("[A-Z]", "_$0", "r")?lower_case}.xml" />
    <instantiate from="src/app_package/ViperFragmentModule.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}FragmentModule.kt"/>
    <instantiate from="src/app_package/ViperFragmentObjectModule.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}FragmentObjectModule.kt"/>
    <instantiate from="src/app_package/ViperPresenter.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Presenter.kt"/>
    <#include "../ViperContract/recipe_contract.xml.ftl" />

    <open file="${srcOut}/${className}Fragment.kt"/>
    <open file="${resOut}/layout/fragment${className?replace("[A-Z]", "_$0", "r")?lower_case}.xml"/>
</recipe>
