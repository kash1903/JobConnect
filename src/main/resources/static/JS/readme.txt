💼 JobConnect - Job Portal Application

Welcome to JobConnect! 🚀
A complete Job Portal System connecting Employers and Job Seekers with smooth, interactive UI built with Thymeleaf, JavaScript, HTML, and CSS, and a robust backend powered by Spring Boot REST APIs.

🤵 Employer use:

usn:employer@example.com
pass:guvi@1234

👀 Job Seeker use:

usn:jobseeker@example.com
pass:guvi@1234


🌟 Features

For Employers 🤵
Post new jobs 📝
Edit or delete existing jobs ✏️🗑️
View applicants for posted jobs 👀
Shortlist or reject candidates ✅❌
Search jobs by keyword or location 🔍
For Job Seekers 👨‍💻
Register and login securely 🔐
Browse jobs based on keywords & location 🏙️
Apply for jobs with a single click 📄
Track application status 📊

Notifications 🔔

Receive notifications using Twilio API for important updates 💬

📂 Frontend Pages

Page	Description
index.html	Landing page 🌐
login.html	User login 🔑
register.html	User registration ✍️
employer.html	Employer dashboard 🤵
jobseeker.html	Job seeker dashboard 👨‍💻

🛠️ Backend APIs

Job Controller - /api/jobs
Method	Endpoint	Description

POST	/api/jobs/post	Post a new job 📝
PUT	/api/jobs/edit/{jobId}	Edit job details ✏️
DELETE	/api/jobs/delete/{jobId}	Delete a job 🗑️
GET	/api/jobs/search	Search jobs 🔍
GET	/api/jobs/employer/{employerId}	Fetch employer's jobs 👨‍💼
GET	/api/jobs/all	Fetch all jobs 🌐


Job Application Controller - /api/applications
Method	Endpoint	Description

POST	/api/applications/apply	Apply for a job 📝
PUT	/api/applications/{id}/status	Update application status ✅❌
GET	/api/applications/user/{userId}	Get all user applications 👤
GET	/api/applications/user/{userId}/status	Get user application statuses 📊
GET	/api/applications/job/{jobId}	Get all applications for a job 📄

User Controller - /api/users
Method	Endpoint	Description

POST	/api/users/register	Register new user ✍️
POST	/api/users/login	Login 🔑
GET	/api/users/{userId}	Get user details 👤
Twilio Controller - /api/notifications
Method	Endpoint	Description
POST	/api/notifications/send	Send notifications 💬

💾 Schemas

Job 🏢
JobApplication 📄
User 👤

⚡ Tech Stack

Backend: Spring Boot, REST API 🌐
Frontend: Thymeleaf, HTML, CSS, JavaScript 🎨
Database: Any relational DB (MySQL/PostgreSQL) 🗄️
Notification: Twilio API 💬

📌 Key Highlights

Fully responsive UI for both Employers and Job Seekers 📱💻
Dynamic job listing with search & filter capabilities 🔍
Secure login & registration system 🔐
Real-time notifications for job applications 🔔
Clean and organized frontend code using Thymeleaf templates 🧩

🚀 How to Run

Clone the repository
git clone <your-repo-url>

Start Backend (Spring Boot)
mvn spring-boot:run
Open Frontend Pages

Access via browser: http://localhost:8080/index.html 🌐

Login/Register and start exploring!

💡 Future Enhancements

Add resume upload feature for job seekers 📄
Enable email notifications 📧
Implement advanced search & filters ⚡
Add analytics dashboard for employers 📊

❤️ Thanks & Contact

Built with 💻, ☕, and ❤️ by K A SUBRAMANIAN

GitHub: https://github.com/kash1903

LinkedIn: https://www.linkedin.com/in/k-a-subramanian/