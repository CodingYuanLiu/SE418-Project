# CI - Jenkins

## The Running CI service

The Jenkins service will be running all the time, here is its role and effect.

Whenever there is a new push to the relevant repo, GitHub will start a new integration of Jenkins through webhook. The commit message will show in the chart. See image below: 

<img src='../imgs/1.jpg'>

If we click on one of them, we will see the interface shown in the following figure. The pipeline stages is graphed at the top of the page. Click any of them will show the detail. And, if there is any error occurs, the information will be shown below.

<img src='../imgs/2.jpg'>

When finishing the CI process, when we make a pull request, a test will be run automately by Jenkins. See picture below.

<img src='../imgs/3.jpg'>