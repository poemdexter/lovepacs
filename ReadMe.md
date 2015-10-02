# Lovepacs App - http://lovepacs.herokuapp.com/

## Development

All development occurs on the `develop` branch.

build (OSX): `./gradlew stage`

build (Win): `gradlew.bat stage`

## Deployment

Heroku is set up to watch the `master` branch on Github.  It will automatically build and deploy.

deploy local: `heroku local web`

accessible at `http://localhost:5000`

get logs: `heroku logs --app lovepacs`