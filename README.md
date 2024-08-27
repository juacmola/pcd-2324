# Exercises of Concurrent and Distributed Programming 
---
##### Disclaimer
The project is intended for educational purpose only.

---
## Contents
- [Exercises of Concurrent and Distributed Programming](#exercises-of-concurrent-and-distributed-programming)
        - [Disclaimer](#disclaimer)
  - [Contents](#contents)
  - [Introduction](#introduction)
    - [Exercise 1](#exercise-1)
    - [Exercise 2](#exercise-2)
    - [Exercise 3](#exercise-3)
    - [Exercise 4](#exercise-4)
  - [Credits](#credits)

---
## Introduction
This repository contains the exercises of the course of Concurrent and Distributed Programming. The course is part of the Degree of Computer Science of the University of Murcia (Universidad de Murcia). This course is being taught in the second semester of the second year of the degree. In this case, our participation in the course was in the academic year 2023/2024. The course was taught by the professor Sergio López Bernal ([LinkedIn](https://www.linkedin.com/in/slopezbernal/?originalSubdomain=es)) ([Investigation Web](https://portalinvestigacion.um.es/investigadores/815441/detalle)).

The project is divided into four exercises. We attached both the code and the report of each exercise. As you can see, we have used the Java programming language to solve the exercises.

The reports and the code comments are written in Spanish. If you have any questions, please do not hesitate to contact us.

---
### Exercise 1
Here it is the statement of the exercise:

    We have 10 consumer processes, a process that generates numbers and operations (product, sum and remainder) and an adder process.
    The generator process alternately introduces a number and an operation, in blocks of 6 numbers and 5 operations, in an array of 110 elements in size (to do this you must encode the operations as integers, for example you can say that 1 = addition, 2 = subtraction and 3 = multiplication).
    Each consumer process has to read 11 consecutive positions of the array and calculate the final result of performing the indicated operations. For example, if in the first 5 positions a process finds the following sequence '3*5+2' the result would be '17'. Once the result of processing the 11 positions has been calculated, it will dump it into another matrix and print its ID and the result it has calculated on the screen. Once all the processes have calculated their part and dumped it into the results vector, the adder process must calculate the sum of all the results and display it on the screen. Solve the exercise in Java using ReentrantLock.



---
### Exercise 2


---
### Exercise 3


---
### Exercise 4


---
## Credits
The project was developed by the following people:
[] - [juacmola](https://github.com/juacmola)
[] - [Effect3](https://github.com/Effect3)

Lastly, this is part of one of the courses of the Degree of Computer Science of the [Universidad de Murcia](https://www.um.es/web/estudios/grados/informatica) - (Universidad de Murcia).
