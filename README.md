-----------------------
Development environment
-----------------------
Android: Android Studio 2.1.2
Android 6.0 "Marshmallow" (API 23)
OS Name: Mac OS X
OS Version: 10.11.4
OS Architecture: x86_64
Total Memory (MB): 61
Max Memory (MB): 910

--------------
Overall Design
--------------
MainActivity: run the program, relating to activity_main.xml
Paint: the whole program’s window
Model: the model dealing with notifying observers
View_Canvas: main canvas that user will draw on, relating to view_canvas.xml
View_Bar: scrollable tool bar controllers, relating to view_bar.xml
Tool_enum: defining tool type
PaintObject: defining objects that user draws
Dot: coordinate class for position

------------
Enhancements
------------
1. Use pinch-to-zoom to resize a single shape: select the shape, then pinch-in or pinch-out to change the size of that shape.

-----
Note
-----
The toolbar is scrollable in case sometime the screen cannot fit its size.