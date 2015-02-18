# Boilr
[![Translation status](https://hosted.weblate.org/widgets/boilr/-/svg-badge.svg)](https://hosted.weblate.org/engage/boilr/?utm_source=widget)

Android application which monitors Bitcoin, cryptocurrencies and bullion prices, and triggers alarms according to user settings.

Supports 2 types of alarms:

* Price Hit - Triggered when price crosses the alarm's upper or lower limit.
* Price Change - Triggered when price changes more than X amount (in currency or percentage) in a specified time frame (e.g. 15 min). It uses a rolling time frame: price is fetched with a given update interval (e.g. 30 s) and compared with the price fetched one time frame ago (e.g. 15 min ago).

Sound and vibration are configurable globally and individually for each alarm. Lists of exchanges, pairs and alarms are fully searchable, allowing a quick alarm setup and configuration. Alarms can be reordered. Disabled alarms keep updating while (and only while) the alarm list is visible, acting as tickers.

Market data is retrieved directly from the exchanges using Wi-Fi or Mobile Data (if allowed). [libdynticker](https://github.com/andrefbsantos/libdynticker) is used to interface with the exchanges. Therefore, Boilr supports all [exchanges available on libdynticker](https://github.com/andrefbsantos/libdynticker/#supported-exchanges), as all their pairs.

To create alarms Boilr uses [libpricealarm](https://github.com/andrefbsantos/libpricealarm).

Other libraries we use: [changeloglib](https://github.com/gabrielemariotti/changeloglib).

Check Boilr's website to see it in action: http://boilr.mobi The website source-code is available at [boilr-site](https://github.com/andrefbsantos/boilr-site).

## Troubleshooting

Trouble | Solution
------- | --------
Alarm not updating when phone is sleeping. | Set `Settings > Wi-Fi > Advanced > Keep Wi-Fi on during sleep` to `Always`

## Building
You need to fill out the [prerequisites for Android Maven Plugin](https://code.google.com/p/maven-android-plugin/wiki/GettingStarted) and to get the Android SDK for API 21. Then run `mvn validate` once to make Maven aware of the plugins we use to download and install dependencies which are not in Maven repositories. For then on you can use:

* `mvn package -P debug` to build an apk in debug mode.
* `mvn package -P release` to build an apk in release mode, which will be optimized, signed and aligned.
* `mvn android:deploy` to install the generated apk through USB in any connected phones.

Note: `mvn package` with no profile associated builds an unsigned release mode apk, unsuitable for deploy. It is meant for further processing by downstream projects like F-Droid.

## Versioning
Boilr follows [Semantic Versioning](http://semver.org) with the API being the user-interface.

## Debuging
On your shell you can use `export ANDROID_LOG_TAGS="ActivityManager:I Boilr:D *:S"` to filter logcat, then run `adb logcat` as usual.

## License and authorship
Boilr code licensed under [GNU GPL v3](/LICENSE) or later. Copyright belongs to [André Filipe Santos](https://github.com/andrefbsantos), [David Ludovino](https://github.com/dllud) and other [contributors listed on GitHub](https://github.com/andrefbsantos/boilr/graphs/contributors), unless otherwise stated.

Concept, design, [icon](src/main/img/icon.ai) and [artwork](src/main/img) by [Ricardo Duarte](http://vimeo.com/ricardoduarte). Icon dual licensed under [CC-BY-SA 4.0 International](https://creativecommons.org/licenses/by-sa/4.0) or [GPLv3+](/LICENSE).

Translators:

* Chinese: [Jan Xie](https://github.com/janx) and Song
* Czech: [Jaroslav Lichtblau](https://github.com/svetlemodry)
* German: Luca Rui
* Italian: Francesca Mautone
* Polish: [Michal Pleban](https://pl.linkedin.com/in/michalpleban)
* Spanish: Mariona Martinez

## Donations and tips
In case you wanna pay us a beer ;)

* BTC: 1PHuSWfuAwR6oz9qV93rTdMVozfM85Qqxx
* XMR: 43KcTwXw5D43YRE8MSG4D7X3S2kjU6ehBABucyKAVNYnKbNxpGWZ8zGbEhCq79N4ZGDtefU5w4AGoeR6eNB5yMbEPhAw3FT
* DOGE: DSSJyAtJy9xokkbfTUQ5NQov8M1WQ8FaYg
* NXT: NXT-PMQL-5RXJ-QE46-2L4SQ
