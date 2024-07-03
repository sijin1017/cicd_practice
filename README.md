# UMC Spring A팀 레포지토리입니다.

1. erd

![My ERD](https://github.com/UMC-CAU-6th/Spring-A/blob/sireal/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202024-06-25%20%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE%204.07.37.png)


Table Member {
  id INTEGER [pk]
  email VARCHAR
  password VARCHAR
  nickname VARCHAR
}

Table Board {
  id INTEGER [pk]
  name VARCHAR
  description VARCHAR
}

Table Post {
  id INTEGER [pk]
  title VARCHAR
  content TEXT
  created_at DATETIME
  updated_at DATETIME
  member_id INTEGER 
  board_id INTEGER
}

Table Comment {
  id INTEGER [pk]
  content TEXT
  created_at DATETIME
  updated_at DATETIME
  member_id INTEGER 
  post_id INTEGER 
}

Ref: Member.id > Post.member_id
Ref: Member.id > Comment.member_id
Ref: Post.id > Comment.post_id
Ref: Board.id > Post.board_id
