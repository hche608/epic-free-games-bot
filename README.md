[![Build Status](https://travis-ci.org/hche608/epic-free-games-bot.svg?branch=master)](https://travis-ci.org/hche608/epic-free-games-bot)
![](https://sonarcloud.io/api/project_badges/measure?project=me.hax3.epic%3Afree-games-bot-parent&metric=alert_status)
![](https://sonarcloud.io/api/project_badges/measure?project=me.hax3.epic%3Afree-games-bot-parent&metric=coverage)
# epic-free-games-bot

This project is not completed yet!!!!

# What
This is a project for getting free games from epic automatically

Also this is a tutorial to show BDD and TDD

# How
## Test
```shell script
mvn test
```

## Build
```shell script
mvn package
```

## Run
```shell script
export login.type=<>
export EPIC_USERNAME=placeholder
export EPIC_PASSWORD=placeholder
mvn verify -Dspring.profiles.active=local
```