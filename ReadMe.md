# Lovepacs App

## Development

All development occurs on the `develop` branch.

build (OSX): `./gradlew stage`

build (Win): `gradlew.bat stage`

deploy local: `heroku local web`

## Deployment

Heroku is set up to watch the `master` branch on Github.  It will automatically build and deploy.

accessible at `http://localhost:8080`

get logs: `heroku logs --app lovepacs`

