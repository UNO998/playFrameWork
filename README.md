# Play Application - TweetAnalytics

## Project Objectives:
- Develop a reactive web Application to Search Relavent tweets by keyword

## Tool && Framework:
- [Play Framework](https://www.playframework.com/)
- Java 8
- [Twitter API](https://developer.twitter.com)
- Test Tool : [Jacoco](https://github.com/sbt/sbt-jacoco)

## Project guidelines
- Based on **Play FrameWork** 
- Build and run through **sbt**
  - [play framework start guideline](./sbtGuide.md)
- Document all method (including private methods)
- Controller Action must be **asynchronous** 
  - Using Java 8 :
    - CompletionStage
    - CompletableFutrue
  - Not using :
    - ~~get()~~
    - ~~join()~~
- Create JUnit

## Project Requirement link :
[project requirement1](./project-require1.pdf)

