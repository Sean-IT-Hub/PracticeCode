
document.addEventListener("DOMContentLoaded", function () {

    // 1. Kunin ang form
    const form = document.querySelector("form");

    // 2. Kunin ang Cancel button
    const cancelBtn = document.querySelector(".cancel-btn");

    // 3. Event kapag nag-submit ang form
    form.addEventListener("submit", function (event) {

        // Pigilan muna ang default submission
        event.preventDefault();

        // 4. Kunin ang values ng personal information
        const firstName = document.getElementById("first_name").value.trim();
        const lastName = document.getElementById("last_name").value.trim();
        const email = document.getElementById("email_address").value.trim();

        const phone = document.getElementById("phone").value.trim();
        const dateOfBirth = document.getElementById("date_of_birth").value;

        // 5. Kunin ang selected gender
        const gender = document.querySelector(
            'input[name="gender"]:checked'
        );

        // 6. Kunin ang department at symptoms
        const department = document.getElementById("department").value;
        const symptoms = document.getElementById("message").value.trim();

        // 7. Validation para sa phone number
        if (phone !== "" && !/^[0-9]{11}$/.test(phone)) {
            alert("Phone number must be exactly 11 digits.");
            return;
        }

        // 8. Validation para sa birthday
        const birthDate = new Date(dateOfBirth);
        const today = new Date();

        if (birthDate > today) {
            alert("Date of birth cannot be in the future.");
            return;
        }

        // 9. Validation para sa gender
        if (!gender) {
            alert("Please select your gender.");
            return;
        }

        // 10. Validation para sa department
        if (department === "") {
            alert("Please select a department.");
            return;
        }

        // 11. Kunin ang gender value
        const selectedGender = gender.value;

        // 12. Confirmation bago ituloy
        const confirmSubmit = confirm(
            "Are you sure you want to register?"
        );

        if (!confirmSubmit) {
            return;
        }

        // 13. I-display ang registered information
        alert(
            "Patient Registered Successfully!\n\n" +
            "Name: " + firstName + " " + lastName + "\n" +
            "Email: " + email + "\n" +
            "Phone: " + (phone || "Not provided") + "\n" +
            "Date of Birth: " + dateOfBirth + "\n" +
            "Gender: " + selectedGender + "\n" +
            "Department: " + department + "\n" +
            "Symptoms: " + (symptoms || "None")
        );

        // 14. Ipakita rin sa browser console
        console.log("Patient Registration");
        console.log("First Name:", firstName);
        console.log("Last Name:", lastName);
        console.log("Email:", email);
        console.log("Phone:", phone);
        console.log("Date of Birth:", dateOfBirth);
        console.log("Gender:", selectedGender);
        console.log("Department:", department);
        console.log("Symptoms:", symptoms);

        // 15. I-reset ang form pagkatapos ng registration
        form.reset();

    });

    // 16. Cancel button
    cancelBtn.addEventListener("click", function () {

        const confirmCancel = confirm(
            "Are you sure you want to clear the form?"
        );

        if (confirmCancel) {
            form.reset();
        }

    });

});