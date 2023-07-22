# Fragment Overview
## Mô tả
* Ứng dụng có 3 màn hình, màn hình start_exercise, set_time và exercise.
* Khi vào ứng dụng sẽ hiển thị màn hình start_exercise, người dùng sẽ chọn 1 trong 2 kiểu luyện tập và sau đó chuyển sang màn set_time
* Sau khi sang màn set_time, người dùng sẽ tùy chọn thời gian bắt đầu và kết thúc. Sau đó người dùng nhấn vào nút "Start" và chuyển sang màn hình luyện tập
* Sau khi sang màn luyện tập, 1 vòng tròn đếm ngược thời gian(thời gian kết thúc - thời gian bắt đầu). Sau khi đếm ngược kết thúc, thông báo cho người dùng bằng toast(hoặc dialog, bottom sheet)
* Giao diện Clone theo thiết kế từ [Figma](https://www.figma.com/file/SZysjZ8Bl0uVN3hiBkml4D/Fragment-Exercise?type=design&node-id=0%3A1&mode=design&t=Bdsan2XLq6Yw2f8K-1)

## Yêu cầu
* Bắt buộc phải sử dụng của Fragment và Activity
* Xử lý các input của người dùng tránh gặp lỗi(VD: thời gian bắt đầu lớn hơn thời gian kết thúc)
* Check out nhánh với tên mình (VD: TuanVH), push code liên tục và tạo pull request.
