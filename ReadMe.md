# [Lovepacs App](http://lovepacs.herokuapp.com/)

## Information

The Lovepacs app is a Spring Boot app built with Gradle.  The Gradle wrapper is used so a local installation of gradle is not necessary.  The Lovepacs app is hosted on Heroku's free dyno using a free ClearDB MySQL database.  It is recommended to install the [Heroku Toolbelt](https://toolbelt.heroku.com/) in order to view logs from Heroku.

All development occurs on the `develop` branch.  Heroku is set up to watch the `master` branch on Github and will be trigger a build and deploy when `master` receives new code.

## Back-End Development

### Configuration

The `src/main/resources/application.yml` needs to be filled with the correct info for connecting to the database.  You can view the database configuration by going to the lovepacs settings page on the Heroku Dashboard and click *Config Vars* or you can run `heroku config -s --app lovepacs` to view the current environment variables.  

In the spring datasource section of `application.yml`, you'll want to replace `url`, `username`, and `password` values with those of `CLEARDB_DATABASE_URL`, `CLEARDB_DATABASE_USERNAME`, and `CLEARDB_DATABASE_PASSWORD` respectively.

**Be careful not to commit your application.yml with the database credentials.**

### Commands

* build (OSX): `./gradlew clean build`
* deploy local (OSX): `./gradlew bootRun`
* build (Win): `gradlew.bat clean build`
* deploy local (Win): `gradlew.bat bootRun`
* view local deploy: http://localhost:5000/
* view remote deploy:  http://lovepacs.herokuapp.com/
* view remote logs: `heroku logs --app lovepacs`

## Front-End Development

All of the front-end development code is located within `src/main/resources`. Run the following command lines in that directory.

#### Dependencies

To get the front-end dependencies run `npm install`.

#### Webpack

To start webpack, call `npm start`.

Webpack is accessible at `http://localhost:5010`

Live reloading accessible at `http://localhost:5010/webpack-dev-server/`

#### Deployment

When you are ready to deploy, run `webpack`.  This will package the front-end code and place it within the static folder.