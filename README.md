
UNDER DEVELOPMENT

# DroiDon

[![wercker status](https://app.wercker.com/status/ef28f812d5da53511d61f3d777505ad1/s/master "wercker status")](https://app.wercker.com/project/byKey/ef28f812d5da53511d61f3d777505ad1)
[![codecov](https://codecov.io/gh/sys1yagi/DroiDon/branch/master/graph/badge.svg)](https://codecov.io/gh/sys1yagi/DroiDon)


DroiDon is android app for [Mastadon](https://github.com/tootsuite/mastodon)

# Language

Kotlin

# Architecture

VIPER Architecture like.

- https://github.com/strongself/The-Book-of-VIPER/blob/master/english/introduction-to-viper.md
- https://www.ckl.io/blog/using-viper-architecture-android/

# SetUp

```sh
git clone git@github.com:sys1yagi/mastodon-android.git

# build
./gradlew app:assembleDebug
```

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

Install VIPER template for Android Studio. [VIPER templates](https://github.com/sys1yagi/mastodon-android/tree/master/templates) is a template for Android Studio which generates necessary files for VIPER architecture.


```sh

cd mastodon-android/templates
./install.sh
```


# Release

TODO

# Contribution

TODO

