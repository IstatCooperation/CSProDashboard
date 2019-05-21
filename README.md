# CSPro Dashboard

CsPro Dashboard is an open-source Java Spring-based web application built on the database created using [CsPro2Sql](https://github.com/mauroIstat/CsPro2Sql). The web application can be easily configured in order to provide out-of-the-box several reports, i.e. age distribution, sex distribution, religion distribution, average number household members, etc. 

## What you’ll need

CsPro Dashboard displays the content of report tables generated using [CsPro2Sql](https://github.com/mauroIstat/CsPro2Sql) and provides information on the data transfer process. Therefore, in order to run the CsPro Dashboard you need:

* A Mysql database containing [CsPro 7.0](http://www.csprousers.org/beta/) plain text files
* To perform the execution steps described in the home page of [CsPro2Sql](https://github.com/mauroIstat/CsPro2Sql) project. Briefly speaking you should transfer the data from the CsPro 7.0 database to the microdata MySQL database

Further, in order to build the CsPro Dashboard application, your environment should fulfill the following requirements:

* A favorite text editor or IDE
* JDK 1.8 or later
* Maven 3.0+

## What you’ll build

You’ll build a web application that will provide out of the box :
* Authentication & authorization;
* Responsive graphical interface (html, css, js):
  * Report tables with enhanced interaction controls (search, export, sorting, etc.);
  * Report charts (bar, pie, doughnut);
* CsPro2Sql reports:
  * Status of the data transfer process (RUNNING/COMPLETED, number of records transferred, etc.)
  * Errors in the data transfer process (number of errors, details on each error, etc.)
* Server side components:
  * CRUD (insert, delete, update);

## How to build

##Create the database
In order to describe the build steps, we assume that your [CsPro2Sql](https://github.com/mauroIstat/CsPro2Sql) property file is the following (eg. `Household.properties`):
```
# Source CsPro database
db.source.uri=jdbc:mysql://localhost:3306
db.source.schema=cspro
db.source.username=srcUsername
db.source.password=srcPassword
db.source.data.table=household_dict

# Destination microdata MySQL
db.dest.uri=jdbc:mysql://localhost:3306
db.dest.schema=cspro_microdata
db.dest.username=dstUsername
db.dest.password=dstPassword
db.dest.table.prefix=h
```
Assuming that your working path is `WORKING_PATH` and that the properties file is stored in your `WORKING_PATH`, perform the following execution steps: 
```
> CsPro2Sql -e schema -p Household.properties –o WORKING_PATH\microdata.sql
> mysql -u dstUsername -p < WORKING_PATH\microdata.sql
> CsPro2Sql -e loader -p Household.properties –cc
```
If you have successfully completed these steps, you have a microdata Mysql database `cspro_microdata` containing the data from the CsPro 7.0 database. Now you are ready to execute the commands that generate the tables used by the CSPro Dashboard.
First of all you need to define the following properties:
```
# monitor config
table.individual=INDIVIDUAL
column.individual.sex=P307
column.individual.age=P308
column.individual.religion=P310
column.individual.sex.value.male=1
column.individual.sex.value.female=2
column.questionnaire.ea=ID101,ID102,ID103,ID104,ID105,ID106,ID107,ID108,ID109
column.questionnaire.ea.name=Region,Zone,Woreda,City,Subcity,Psa,Sa,Kebele,EA
column.questionnaire.ea.description=P304A,,,,,,,,
range.individual.age=0,5,11,17,26,35,46,56,66,79,100
```

These properties are used by CsPro2Sql monitor engine in order to generate the report tables. Now you can execute the following commands:
```
> CsPro2Sql -e monitor -p Household.properties –o WORKING_PATH\dashboard.sql
> mysql -u dstUsername -p < WORKING_PATH\dashboard.sql
> CsPro2Sql -e update -p Household.properties –cc
```

The script will populate the `USER/ROLES` table with two users:
```
Username: admin@dashboard.it
Password: dashboard
Role: ADMIN

Username: guest@dashboard.it
Password: dashboard
Role: GUEST
``` 
##Build the web application
Download the source code and open it with your favorite IDE.
As a first step check the content of the application.properties file, located in the path `Other Sources > src/main/resources`:

```
spring.datasource.url = jdbc:mysql://localhost:3306/cspro_microdata?useSSL=false
spring.datasource.username = dstUsername
spring.datasource.password = dstPassword
```
The properties should match the `Destination microdata MySQL` properties specified in the `Household.properties`

Now you can perform your first build of the application.
If the build process ends successfully, you are ready to run the application. 
The application is built using the open source framework Spring Boot, which generates an executable jar (that can be run from the command line). Spring Boot creates a stand-alone Spring based Applications, with an embedded Tomcat, that you can "just run".
```
java –jar csprodashboard.jar
```

It is also possible to create a war file that can be deployed on a servlet container such as Tomcat. Simply modify the maven build profile to DashboardWar.

## Acknowledgement
The team responsible of [Census and Survey Processing System (CSPro)](https://www.census.gov/population/international/software/cspro/) 

## License
CSProDashboard is EUPL-licensed
