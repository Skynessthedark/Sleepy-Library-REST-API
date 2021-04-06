# [Sleepy Library](https://sleepy-library-api.herokuapp.com/)

##  <img src="https://wiki.faforever.com/images/2/21/Java_logo.jpg" height="100" width="200"> <img src="https://upload.wikimedia.org/wikipedia/commons/f/f5/Maven_logo.gif" height="100" width="200"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/1280px-Spring_Framework_Logo_2018.svg.png" height="100" width="200"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/1200px-Postgresql_elephant.svg.png" height="100" width="200"> <img src="https://raw.githubusercontent.com/docker-library/docs/c350af05d3fac7b5c3f6327ac82fe4d990d8729c/docker/logo.png" height="100" width="200"> <img src="https://upload.wikimedia.org/wikipedia/commons/8/89/Logo_di_Heroku.png" height="100" width="200"> <img src="https://miro.medium.com/max/690/1*aKVg84SP5oPV9fwOnbl6yQ.png" height="100" width="200">

#### [DB DIAGRAM](https://dbdiagram.io/d/602becd380d742080a3abef0)

#### [Docker Hub](https://hub.docker.com/repository/docker/skynessthedark/sleepy-library-api/general)

### CRUD OPERATIONS

#### AUTHOR
| URL | HTTP | FUNCTIONALITY |
|--|--|--|
| **'/authors'** | GET | Retrieve All Authors |
| **'/authors'** | POST | Create A New Author |
| **'/authors/{authorId}'** | GET | Retrieve An Author by ID |
| **'/authors'** | PUT | Update An Author|
| **'/authors/{authorId}'** | DELETE | Delete An Author by ID|
| **'/authors/books/{authorId}'** | GET | Retrieve the Books of An Author by ID|

#### PUBLISHER
| URL | HTTP | FUNCTIONALITY |
|--|--|--|
| **'/publishers'** | GET | Retrieve All Publishers |
| **'/publishers'** | POST | Create A New Publisher |
| **'/publishers/{publisherId}'** | GET | Retrieve An Publisher by ID |
| **'/publishers'** | PUT | Update An Publisher|
| **'/publishers/{publisherId}'** | DELETE | Delete An Publisher by ID|
| **'/publishers/books/{publisherId}'** | GET | Retrieve the Books of An Publisher by ID|

#### SERIES
| URL | HTTP | FUNCTIONALITY |
|--|--|--|
| **'/series'** | POST | Create A New Series |
| **'/series'** | PUT | Update A Series|
| **'/series/{seriesId}'** | DELETE | Delete A Series by ID|
| **'/series/byId/{seriesId}'** | GET | Retrieve A Series by ID |
| **'/series/byName/{seriesName}'** | GET | Retrieve Series by Name |
| **'/series'** | GET | Retrieve All Series |
| **'/series/books/{seriesId}'** | GET | Retrieve the Books of A Series by ID|

#### BOOK
| URL | HTTP | FUNCTIONALITY |
|--|--|--|
| **'/books'** | POST | Create A New Book |
| **'/books'** | PUT | Update A Book by ID |
| **'/books'** | GET | Retrieve All Books |
| **'/books/{bookId}'** | GET | Retrieve A Book by ID |
| **'/books/byIsbn/{bookIsbn}'** | GET | Retrieve A Book by ISBN |
| **'/books/{bookId}'** | DELETE | Delete A Book by ID|
| **'/books/series/add/{bookId}'** | PUT | Add A Series to A Book by ID |
| **'/books/authors/add/{bookId}'** | PUT | Add An Author to A Book by ID |
| **'/books/publishers/add/{bookId}'** | PUT | Add A Publisher to A Book by ID |
| **'/books/series/remove/{bookId}'** | PUT | Add A Series to A Book by ID |
| **'/books/authors/remove/{bookId}'** | PUT | Add An Author to A Book by ID |
| **'/books/publishers/remove/{bookId}'** | PUT | Add A Publisher to A Book by ID |
| **'/books/{bookId}'** | PUT | Update A Book by ID |
| **'/books/authors/{bookId}'** | GET | Get Authors of A Book by Given Book ID|
| **'/books/publishers/{bookId}'** | GET | Get Publishers of A Book by Given Book ID|
