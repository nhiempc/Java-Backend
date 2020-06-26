CREATE DATABASE IF NOT EXISTS student_cms_plusplus CHARACTER SET `utf8mb4`;
CREATE TABLE student(
	`id` INT(6) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(30) NOT NULL,
	`mssv` VARCHAR(10) NOT NULL,
	`password` VARCHAR(30) NOT NULL,
	`phone` VARCHAR(20) NOT NULL,
	`address` VARCHAR(50) NOT NULL,
	`age` INT(6) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`created_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`last_updated_timestamp` NOT NULL DEFAULT `0000-00-00 00:00:00`
);
CREATE TABLE class(
	`id` INT(6) AUTO_INCREMENT PRIMARY KEY,
	`name` VARCHAR(30) NOT NULL,
	`major` VARCHAR(50) NOT NULL,
	`total_student` INT(6) NOT NULL,
	`teacher_name` VARCHAR(30) NOT NULL,
	`teacher_phone` VARCHAR(20) NOT NULL,
	`created_timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`last_updated_timestamp` NOT NULL DEFAULT `0000-00-00 00:00:00`
);
CREATE TABLE student_class(
	`student_id` INT(6),
	CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
	`class_id` INT(6),
	CONSTRAINT fk_class FOREIGN KEY (class_id) REFERENCES class(id)
);
INSERT INTO student VALUES(1,`Nhiệm`,`HAUI01`,`123`,`0969029224`,`Hưng Yên`,24,`nhiem10596@gmail.com`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO student VALUES(2,`Linh`,`HAUI02`,`456`,`0969147651`,`Hà Nội`,23,`linhdang0204@gmail.com`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO student VALUES(3,`Dung`,`HAUI03`,`789`,`0969475135`,`Hải Dương`,21,`tranvandung@gmail.com`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO class VALUES(4,`Công Nghệ Thông Tin 1`,`Công Nghệ Thông Tin`,50,`An Văn Minh`,`0331475356`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO class VALUES(5,`Kĩ Thuật Phần Mềm 4`,`Công Nghệ Thông Tin`,60,`Lê Trung Hiếu`,`0361685429`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO class VALUES(6,`Kế Toán 3`,`Kê Toán`,65,`Đinh Thu Hương`,`0374257624`,CURRENT_TIMESTAMP(6),CURRENT_TIMESTAMP(6));
INSERT INTO student_class VALUES(1,5);
INSERT INTO student_class VALUES(2,4);
INSERT INTO student_class VALUES(3,6);