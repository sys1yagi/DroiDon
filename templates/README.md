## About

VIPER activity template files for Android Studio.  
You can create classes and layout related VIPER architecture from template.

<img width="467" alt="2017-04-16 11 28 12" src="https://cloud.githubusercontent.com/assets/749051/25069326/2a2b672e-22b9-11e7-872c-2567ea39d116.png">

<img width="800" alt="new_android_component" src="https://cloud.githubusercontent.com/assets/749051/25069334/700ec254-22b9-11e7-900e-2b0afad4ef0c.png">

<img width="307" alt="2017-04-16 15 31 34" src="https://cloud.githubusercontent.com/assets/749051/25069348/d1ca382a-22b9-11e7-9276-ba03c18be5f7.png">

## Install

Run install script and restart Android Studio.

```bash
./install.sh
```

## Usage

1. Select package to create files
2. Select File > New > VIPER template > VIPER Activity
3. Input view function class name like `TootDetail`, and appplication id, package name.
4. Click Finish
5. Add ActivityModule to AppComponent

```kotlin
@Singleton
@Component(modules = arrayOf(
        // ...
        TootDetailActivityModule::class
))
interface AppComponent {

    fun inject(target: MastodonAndroidApplication)
}
```

6. Add Activity to AndroidManifest.xml

```xml
<activity android:name=".ui.tootdetail.TootDetail"
  android:exported="false"
/>
```

## Reference

https://riggaroo.co.za/custom-file-template-group-android-studiointellij/
