# [Lovepacs App](http://lovepacs.herokuapp.com/)

## Information

The Lovepacs app is a Spring Boot app built with Gradle.  The Gradle wrapper is used so a local installation of gradle is not necessary.  The Lovepacs app is hosted on Heroku's free dyno using a free ClearDB MySQL database.  It is recommended to install the [Heroku Toolbelt](https://toolbelt.heroku.com/) in order to view logs from Heroku.

## Development

All development occurs on the `develop` branch.  Heroku is set up to watch the `master` branch on Github and will be trigger a build and deploy when `master` receives new code.

### Setup

The `src/main/resources/application.yml` needs to be filled with the correct info for connecting to the database.  You can view the database configuration by going to the lovepacs settings page on the Heroku Dashboard and click *Config Vars* or you can run `heroku config -s --app lovepacs` to view the current environment variables.  In the spring datasource section of `application.yml`, you'll want to replace `url`, `username`, and `password` values with those of `CLEARDB_DATABASE_URL`, `CLEARDB_DATABASE_USERNAME`, and `CLEARDB_DATABASE_PASSWORD` respectively.

**Be careful not to commit your application.yml with the database credentials.**

### Commands

* build (OSX): `./gradlew clean build`
* deploy local (OSX): `./gradlew bootRun`
* build (Win): `gradlew.bat clean build`
* deploy local (Win): `gradlew.bat bootRun`
* view local deploy: http://localhost:5000/
* view remote deploy:  http://lovepacs.herokuapp.com/
* view remote logs: `heroku logs --app lovepacs`