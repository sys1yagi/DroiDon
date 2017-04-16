## About

VIPER activity template files for Android Studio.  
You can create classes and layout related VIPER architecture from template.

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
