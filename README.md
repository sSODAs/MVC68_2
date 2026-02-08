# MVC

## a. ไฟล์ใดทำหน้าที่อะไรใน MVC และทำงานร่วมกันอย่างไร
**1. Model (จัดการข้อมูลและ Business Logic)**
  - Database.java  ทำหน้าที่เป็น แหล่งข้อมูลกลาง  โหลดข้อมูลจากไฟล์ JSON ได้แก่  canteens.json ,   stalls.json ,  complaints.json และแปลงข้อมูล JSON เป็น Object ของ Model (Canteen, Stall, Complaint) โดยตัวของ  Controller จะดึงข้อมูลจาก Database เท่านั้น View ไม่สามารถเข้าถึง Database โดยตรง
  
  - Canteen.java   แทนข้อมูล โรงอาหาร  เก็บ id, name, location  ใช้เชื่อมความสัมพันธ์กับร้านอาหาร (Stall) ผ่าน canteenId

  - Stall.java    แทนข้อมูล ร้านอาหาร เก็บ id, name, canteenIdใช้เชื่อมกับ Complaint ผ่าน stallId และใช้เป็น key ในการสรุปจำนวนการร้องเรียน

  - Complaint.java  แทนข้อมูล การร้องเรียน โดยจะเก็บข้อมูล วันที่ประเภท รายละเอียด สถานะ  รองรับ หลายการตอบกลับ ผ่าน List<Response>  มีเมธอด  addResponse() → เพิ่มการตอบกลับ  และ  setStatus() → เปลี่ยนสถานะตาม Business Rule
  
  - Response.java  แทนข้อมูล การตอบกลับจากผู้ดูแล  เก็บวันที่และข้อความตอบกลับ  และ  ใช้ประกอบใน Complaint (1 Complaint มีหลาย Response)
  
  - ComplaintService.java   เป็น Business Logic Layer  ทำหน้าที่สรุปข้อมูลการร้องเรียน  โดยจะมีเมธอด  summarizeByCanteen(...)  แยก logic การคำนวณออกจาก Controller จะไม่ยุ่งกับ View หรือ GUI
  
**2.  View (แสดงผลอย่างเดียว ไม่มี Logic)**
  - MainFrame.java   เป็นหน้าต่างหลักของโปรแกรมController ใช้ setView() เพื่อสลับหน้าจอ View แต่ละหน้าจะถูกแสดงทีละหน้าตาม flow

  - ComplaintListView.java  แสดง รายการร้องเรียนทั้งหมดเรียงตามวันที่ และมีการส่ง Event กลับไปที่ Controller เมื่อผู้ใช้เลือก ex.ดูรายละเอียดการร้องเรียน ไปหน้าสรุปร้านอาหาร

  - ComplaintDetailView.java   แสดง รายละเอียดของการร้องเรียน ex. วันที่ / ประเภท / สถานะ / รายละเอียด / การตอบกลับทั้งหมด โดยหน้านี้จะมีปุ่ม ตอบกลับ และ กลับไปหน้ารายการ  (แสดงข้อมูลตามสถานะปัจจุบันของ Complaint)

  - ComplaintResponseView.java    ใช้สำหรับ เพิ่มการตอบกลับ  ผู้ดูแลระบบกรอกข้อความตอบกลับ   เมื่อกดบันทึก ->  ส่งข้อมูลให้ Controller

  - StallSummaryView.java  แสดง สรุปจำนวนการร้องเรียน แยกตาม โรงอาหาร → ร้านอาหาร → จำนวนการร้องเรียน โดยใช้ข้อมูลที่ Controller ส่งมาเท่านั้น (ไม่มี logic การนับเอง)
  
**3. Controller (ควบคุม Flow และประสานงาน)**
  - ComplaintController.java เป็นตัวกลางระหว่าง Model และ View ควบคุมการเปลี่ยนหน้าจอทั้งหมด ทำหน้าที่: ดึงข้อมูลจาก Database / เรียกใช้ ComplaintService / สร้าง View และส่งข้อมูลให้ View จัดการ Business Rule เช่น เมื่อมีการตอบกลับ → เปลี่ยนสถานะเป็น “ดำเนินการแล้ว” และ หลังตอบกลับ → กลับไปหน้ารายละเอียด

## b.สรุป Routes / Actions หลัก และหน้าจอ View

  1. เปิดระบบ ->  ComplaintListView
  2. เลือกรายการร้องเรียน -> ComplaintDetailView
  3. กดตอบกลับ -> ComplaintResponseView
  4. บันทึกการตอบกลับ -> กลับไป ComplaintDetailView
  5. ดูสรุปร้านอาหาร -> StallSummaryView
  6. กดกลับ -> ComplaintListView

## การ build และ run
```bash
# Clone
git clone https://github.com/sSODAs/MVC68_2.git
cd MVC68_2

# Build & Run
javac -encoding UTF-8 -d bin src\Main.java src\controller\*.java src\model\*.java src\view\*.java
java -cp bin Main
```
