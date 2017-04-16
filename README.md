
UNDER DEVELOPMENT

# mastodon-android

[![wercker status](https://app.wercker.com/status/f1349138cc7a3c6ba5275ee2136560ed/s/master "wercker status")](https://app.wercker.com/project/byKey/f1349138cc7a3c6ba5275ee2136560ed)
[![codecov](https://codecov.io/gh/sys1yagi/mastodon-android/branch/master/graph/badge.svg)](https://codecov.io/gh/sys1yagi/mastodon-android)

Mastadon  https://mastodon.social

# Language

Kotlin

# Architecture

VIPER Architecture like.

- https://github.com/strongself/The-Book-of-VIPER/blob/master/english/introduction-to-viper.md
- https://www.ckl.io/blog/using-viper-architecture-android/

# SetUp

```sh
git clone git@github.com:sys1yagi/mastodon-android.git

# install VIPER template for Android Studio
cd mastodon-android/templates
./install.sh
cd ../

# build
./gradlew app:assembleDebug
```

[VIPER templates](https://github.com/sys1yagi/mastodon-android/tree/master/templates) is a template for Android Studio which generates necessary files for VIPER architecture.

# Test

```sh
# app test
./gradlew app:testDebugUnitTest

# mastodon4j test
./gradlew mastodon4j:test

# mastodon4j-rx test
./gradlew mastodon4j-rx:test
```

# Development

TODO

# Release

TODO

# Contribution

TODO

