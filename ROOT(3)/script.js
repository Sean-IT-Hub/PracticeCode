// PART I — Variables, Arrays, and Functions


// const dahil hindi natin papalitan ang array reference.
// Maaari pa rin nating dagdagan o bawasan ang laman ng array.
const students = [];


// Closure-based ID generator
function makeIDGenerator() {

    let counter = 0;

    return function () {

        counter++;

        return counter;
    };
}


const generateID = makeIDGenerator();


// Add student function
function addStudent(name, grade) {

    const student = {
        id: generateID(),
        name: name,
        grade: Number(grade)
    };

    students.push(student);
}


// Render student list
function renderList() {

    const studentList = document.getElementById("studentList");

    // Clear the current list
    studentList.innerHTML = "";


    // Display every student
    students.forEach(function (student) {

        const li = document.createElement("li");

        li.textContent =
            "ID: " + student.id +
            " | " + student.name +
            " - Grade: " + student.grade;

        studentList.appendChild(li);
    });


    // Update statistics
    updateStatistics();
}



// PART II — DOM Manipulation and Events


// Select Add Student button
const addBtn = document.getElementById("addBtn");


// Add Student button event
addBtn.addEventListener("click", function () {

    const name =
        document.getElementById("studentName").value;

    const grade =
        document.getElementById("studentGrade").value;


    // Validate empty fields
    if (name.length === 0 || grade.length === 0) {

        alert("Please enter the student name and grade.");

        return;
    }


    // Add student
    addStudent(name, grade);


    // Update student list
    renderList();


    // Clear input fields
    document.getElementById("studentName").value = "";

    document.getElementById("studentGrade").value = "";
});



// Clear All button
const clearBtn = document.getElementById("clearBtn");


clearBtn.addEventListener("click", function () {

    // Empty the students array
    students.length = 0;


    // Re-render the empty list
    renderList();
});



// PART III — Array Functions


function updateStatistics() {


    // 8. Use map() to get grades only
    const grades = students.map(function (student) {

        return student.grade;
    });


    // If there are no students
    if (grades.length === 0) {

        document.getElementById("avgDisplay").textContent = "-";

        document.getElementById("highDisplay").textContent = "-";

        document.getElementById("lowDisplay").textContent = "-";

        document.getElementById("passDisplay").textContent = "-";

        return;
    }


    // 9. Use reduce() to calculate the average
    const total = grades.reduce(function (sum, grade) {

        return sum + grade;

    }, 0);


    const average = total / grades.length;


    document.getElementById("avgDisplay").textContent =
        average.toFixed(2);


    // 10. Find highest grade
    const highest = Math.max(...grades);


    document.getElementById("highDisplay").textContent =
        highest;


    // 11. Find lowest grade
    const lowest = Math.min(...grades);


    document.getElementById("lowDisplay").textContent =
        lowest;


    // 12. Use filter() to count passing students
    const passingStudents = students.filter(function (student) {

        return student.grade >= 75;
    });


    document.getElementById("passDisplay").textContent =
        passingStudents.length;
}



// PART IV — Sorting and Closures


// Select Sort button
const sortBtn = document.getElementById("sortBtn");


// Sort students from highest grade to lowest
sortBtn.addEventListener("click", function () {

    students.sort(function (a, b) {

        return b.grade - a.grade;
    });


    // Display sorted list
    renderList();
});