# Lovepacs App - http://lovepacs.herokuapp.com/

## Development

All development occurs on the `develop` branch.

build (OSX): `./gradlew stage`

build (Win): `gradlew.bat stage`

### Front-end

All of the front-end development code is located within `src/main/resources`. Run the following command lines in that directory.

#### Dependencies

To get the front-end dependencies run `npm install`.

#### Webpack

To start webpack, call `npm start`.

Webpack is accessible at `http://localhost:5010`

Live reloading accessible at `http://localhost:5010/webpack-dev-server/`

#### Deployment

When you are ready to deploy, run `webpack`.

This will package the front-end code and place it within the static folder.

## Deployment

Heroku is set up to watch the `master` branch on Github.  It will automatically build and deploy.

deploy local: `heroku local web`

accessible at `http://localhost:5000`

get logs: `heroku logs --app lovepacs`