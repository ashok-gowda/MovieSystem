# MovieReviewSystem
The main focus of this project was to provide a platform where movie lovers can come, discover new movies, come to get to know as to how
good or bad the movies are from the reviews assigned by previous commentors, comment on the movies and get recommendations.
Recommendations is based on similarity of users by collaborative filtering using Pearson's formula. In this case we show the user how the most similar user has commented on the movies he has watched and the ratings he has given both the 5 star ratings as well as 1 star ratings and give him the opportunity to pick up the movies he liked as well as avoid the lowly ranked ones

You Need Eclipse for the easiest installation
1) First Extract the zip out into the folder on the disc
2) Then Open Eclipse If Maven integration and Jetty Integration is not present please open Eclipse Marketplace, integrate with Eclipse
3) Replace the spring-servlet.xml file in location org\src\main\webapp\WEB-INF with the one present in FilesToPaste folder
   What this essentially does is replace the Heroku Database Configuration Settings with the local configuration settings
4) Then open your local MySQL database and create a database called theatre. If you want to keep a different name do it and import the database from the TheatreRecommendationSystem\scripts\database.sql 
5)Change the values given the bean configuration in the new sprint servelet.xml
<bean id="dataSource"
         class="org.springframework.jdbc.datasource.DriverManagerDataSource">

		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/theatre" /> !--Keep your database url--!
		<property name="username" value="root" />!-- Keep your username-
		<property name="password" value="root" />!--Keep your database password
	</bean>
6) Then Right Click Select As Run With Jetty 
7) Open http://127.0.0.1:8080/user/ and the login screen opens
8) Create a new user and click away
