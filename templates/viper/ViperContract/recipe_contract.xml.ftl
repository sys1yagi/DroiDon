<recipe folder="root://viper/ViperContract">

    <instantiate from="root/src/app_package/ViperContract.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Contract.kt"/>
    <instantiate from="root/src/app_package/ViperPresenter.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Presenter.kt"/>
    <instantiate from="root/src/app_package/ViperViewModel.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}ViewModel.kt"/>
    <instantiate from="root/src/app_package/ViperInteractor.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Interactor.kt"/>
    <instantiate from="root/src/app_package/ViperRouter.kt.ftl"
                 to="${escapeXmlAttribute(srcOut)}/${className}Router.kt"/>
</recipe>
