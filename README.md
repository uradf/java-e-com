Version 1.0.0

# Summary Assignment Workshop
- Flow การทำงาน Controller > Service > Repository
- Validate JWT Token สำหรับบาง API (Implement ในระดับ Filter ไม่เสร็จ ตอนนี้ทำในระดับ Service)
- Connect to Public API สำเร็จจำลองข้อมูลเงินใน Wallet
- Test แค่ระดับ DataJPA

# Prerequisites for testing
* Internet connection สำหรับดึงค่าเงินจาก public API
* Java 11
* Spring boot 2.6.4


# Scenario

* [X] User กดเรียกดูสินค้า โดยระบบจะแสดงสินค้าหน้าละ 5 ชิ้น
* [X] User กดดูสินค้าหน้าที่ 2
* [X] User ไม่เจอสินค้าที่ต้องการ เลยทำการค้นหาสินค้าจากชื่อ โดยใส่คำว่า Submarine
* [X] System แสดงสินค้าที่ชื่อว่า Submarine (ชื่อ ราคา คำอธิบาย)
* [X] User ทำการหยิบสินค้าชิ้นนี้ใส่ตะกร้า แต่ระบบบังคับให้เข้าสู่ระบบ
* [X] (User ทำการเข้าระบบ)
* [X] User ทำการหยิบสินค้าชิ้นนี้ใส่ตะกร้า
* [X] User กดสั่งซื้อสินค้า และ System แสดงรายละเอียดสินค้าในตะกร้า (ชื่อ ราคา)
* [ ] User เลือกที่อยู่จัดส่งจากใน Platform หรือ กรอกใหม่เอง
* [X] User เลือกจ่ายด้วย External Wallet address และ ยืนยันการสั่งซื้อ (System ทำการตัดเงินและ Stock) และระบบจะแสดงใบเสร็จ


# API List

> Product &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /product?id=1 &nbsp;&nbsp;&nbsp;&nbsp; ดึง Product ทีละ 5 ชิ้น

> Product &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /products?page=0 &nbsp;&nbsp;&nbsp;&nbsp; ดึง Product ทีละ 5 ชิ้น

> Product &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /products/by?name= &nbsp;&nbsp;&nbsp;&nbsp; ดึง Product ทีละ 5 ชิ้น ตามชื่อ Product

> Customer &nbsp;&nbsp;&nbsp;&nbsp; POST &nbsp;&nbsp;&nbsp;&nbsp; /auth &nbsp;&nbsp;&nbsp;&nbsp; สำหรับดึง JWTToken
> > {<br>
> > &nbsp;&nbsp;&nbsp;&nbsp;"username": "prayut",<br>
> > &nbsp;&nbsp;&nbsp;&nbsp;"password": "P@ssw0rd1"<br>
> > }

> Customer &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /address?token=JWT &nbsp;&nbsp;&nbsp;&nbsp; สำหรับดึง User Address

> BasketProduct &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /basket?token=JWT &nbsp;&nbsp;&nbsp;&nbsp; สำหรับดึงข้อมูลตะกร้าสินค้า

> BasketProduct &nbsp;&nbsp;&nbsp;&nbsp; POST &nbsp;&nbsp;&nbsp;&nbsp; /addProduct &nbsp;&nbsp;&nbsp;&nbsp; เพิ่มสินค้าลงตะกร้า
> > {<br>
> > &nbsp;&nbsp;&nbsp;&nbsp;"product": 15,<br>
> > &nbsp;&nbsp;&nbsp;&nbsp;"customer": 1,<br>
>  > &nbsp;&nbsp;&nbsp;&nbsp;"token": "JWT_STRING"<br>
> > }

> Transaction &nbsp;&nbsp;&nbsp;&nbsp; GET &nbsp;&nbsp;&nbsp;&nbsp; /purchase?token=JWT &nbsp;&nbsp;&nbsp;&nbsp; สำหรับดึงข้อมูล Transaction ที่สำเร็จแล้ว

> Transaction &nbsp;&nbsp;&nbsp;&nbsp; POST &nbsp;&nbsp;&nbsp;&nbsp; /purchase &nbsp;&nbsp;&nbsp;&nbsp; ยืนยันการสั่งซื้อสินค้าที่อยู่ในตะกร้า (BasketProduct      )
> > {<br>
>  > &nbsp;&nbsp;&nbsp;&nbsp;"token": "JWT_STRING"<br>
> > }