[![Build Status](https://travis-ci.org/hche608/epic-free-games-bot.svg?branch=master)](https://travis-ci.org/hche608/epic-free-games-bot)
![](https://sonarcloud.io/api/project_badges/measure?project=me.hax3.epic%3Afree-games-bot-parent&metric=alert_status)
![](https://sonarcloud.io/api/project_badges/measure?project=me.hax3.epic%3Afree-games-bot-parent&metric=coverage)
# epic-free-games-bot

## pre requirements

chrome, chromedriver

### TL;DR
This is a project for getting free games from epic automatically by using selenium, chromedriver and chrome.

This bot does not use api directly, it simulates user real actions.

Also this is a tutorial to show BDD and TDD by using (spring, spring-boot, selenium, junit)

## TODO
* ~~login-with-epic~~
* ~~find latest free game from epic~~
* ~~checkout the free game~~ (need to verify)
* ~~implement aws lambda with layer of headless chrome and selenium.~~
* ~~support login-with-facebook~~
* create aws cloudformation templates
* build an stand-alone jar
* create github workflow to build and deploy aws lambda
* create github workflow to invoke aws lambda on schedule-based time expresion
* support login-with-google
* support login-with-psn
* support login-with-xbl
* support login-with-nintendo

## FYI
headless chrome(v78) and chromedriver(v78) for linux are under /bin folder

## Test
```shell script
mvn test
```

## Build
```shell script
mvn package
```

### Run with headless chrome
```shell script
export login.type=< EPCI | FACEBOOK | GOOGLE >
export USERNAME={epic:'placeholder',facebook:'placeholder',google:'placeholder'}
export PASSWORD={epic:'placeholder',facebook:'placeholder',google:''}
mvn verify 
```

### Run with normal chrome
```shell script
export login.type=< EPCI | FACEBOOK | GOOGLE >
export USERNAME={epic:'placeholder',facebook:'placeholder',google:'placeholder'}
export PASSWORD={epic:'placeholder',facebook:'placeholder',google:''}
mvn verify -Dweb.driver=chrome
```