<div dir=rtl>
شما بگو (پرسش و پاسخ)
======

شما بگو، بستر پرسش و پاسخی‌ست بر مبنای [ماموت](https://github.com/caelum/mamute).


## تنظیم ارسال رایانامه
<div dir=ltr>

```
mailer.implementation = br.com.caelum.vraptor.simplemail.DefaultMailer
vraptor.simplemail.main.error-mailing-list = shomabegoo-mail@googlegroups.com
vraptor.simplemail.main.from = shomabegoo.com@gmail.com
vraptor.simplemail.main.from.name = شما بگو

vraptor.simplemail.main.server = smtp.gmail.com
vraptor.simplemail.main.port = 587
vraptor.simplemail.main.tls = true
vraptor.simplemail.main.username = shomabegoo.com@gmail.com
vraptor.simplemail.main.password = password
```

<div dir=rtl>
برای اعمال متغیرهای مورد نیاز، به جای تغییر mamute.properties تنها کافی‌ست که environment.properties (و اگر در حالت توسعه هستید development.properties) را تغییر دهید.
