# SE418-Project: Cloud Native
## Basic Information
### Team Members
* Liu Qingyuan（柳清源）
* Zhao Shenglong（赵胜龙）
* Jin Jiazhen（荆家振）
* Dai Fangyue（戴方越）
### Idea
Crawl the data from Tongqu website with web crawler, analyse the content(titles of the activities for example), find out some useful information and demonstrate it on our frontend pages.
### Technique stack
* Spring Boot
* Docker
* Vue
* OAuth
* Jenkins + BlueOcean
* Python + Selenium (For crawler)
### Coding Standard
[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

[PEP - Python Style Guide](https://www.python.org/dev/peps/pep-0008/)
## Brief Introduction
### Basic Function
Our project can be seen here:

[素拓活动推荐系统](http://47.102.101.146:11000)


![avatar](./imgs/14.png)

We have only one user so you don't need to input the username and the password. Click "登陆" button and you will see:

![avatar](./imgs/15.png)

![avatar](./imgs/16.png)

We have crawled the data from "tongqu" and parsed it. The page shows the result. Simply click the title of the activity at the home page to jump to the targeting activity page.

![avatar](./imgs/17.png)

### Structure
The microservices of the project are in ./project folder as bellow:
![avatar](./imgs/11.png)

The Eureka page is at http://47.102.101.146:9000/, from which you can see where some of our microservices are deployed:
![avatar](./imgs/18.png)

### Jenkins
Jenkins uses ./project/build.sh,./project/test.sh,./project/deliver.sh,./project/run.sh in sequence to deploy the services. The crawler is deployed on another server.

Username and password of our Jenkins are both admin1.

[Our Jenkins](http://47.102.101.146:8080) 

![avatar](./imgs/12.png)

![avatar](./imgs/13.png)