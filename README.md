ToDoList Android App (SQLite)
1. Giới thiệu

Đây là ứng dụng ToDoList trên nền tảng Android, được xây dựng bằng ngôn ngữ Kotlin.
Ứng dụng cho phép người dùng đăng ký tài khoản, đăng nhập và quản lý các công việc hằng ngày của mình.
Dữ liệu người dùng và công việc được lưu trữ cục bộ bằng SQLite.

2. Chức năng chính

Ứng dụng bao gồm các chức năng sau:

Đăng ký tài khoản mới

Đăng nhập vào hệ thống

Thêm công việc mới

Xem danh sách công việc theo từng tài khoản

Lưu trữ dữ liệu bằng cơ sở dữ liệu SQLite

3. Công nghệ sử dụng

Ngôn ngữ lập trình: Kotlin

Nền tảng: Android

Cơ sở dữ liệu: SQLite

IDE: Android Studio

4. Cấu trúc ứng dụng
4.1 Các Activity

LoginActivity: Màn hình đăng nhập

RegisterActivity: Màn hình đăng ký

MainActivity: Hiển thị danh sách công việc

AddTaskActivity: Thêm công việc mới

4.2 Cơ sở dữ liệu

Bảng users

id

username

password

Bảng tasks

id

user_id

title
