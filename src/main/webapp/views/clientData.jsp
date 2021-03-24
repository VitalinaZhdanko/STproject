<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Personal data</title>
</head>
<body>
<h1>Enter data</h1>
<form action="clientData" method="post">
    <table style="with: 50%">
        <tr>
            <td>FIO</td>
            <td><input type="text" name="fio" /></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td>Passport number</td>
            <td><input type="text" name="passportNumber" /></td>
        </tr>
        <tr>
            <td>Identification Number</td>
            <td><input type="text" name="identificationNumber" /></td>
        </tr>
        <tr>
            <td>Date of issue</td>
            <td><input type="date" name="dateOfIssue" /></td>
        </tr>
        <tr>
            <td>Validity period</td>
            <td><input type="text" name="validityPeriod" /></td>
        </tr>
        <tr>
            <td>Issued by whom</td>
            <td><input type="text" name="issuedByWhom" /></td>
        </tr>
        <tr>
            <td>Residence address</td>
            <td><input type="text" name="residenceAddress" /></td>
        </tr>
        <tr>
            <td>License number</td>
            <td><input type="text" name="licenseNumber" /></td>
        </tr>
        <tr>
            <td>License category</td>
            <td><input type="text" name="licenseCategory" /></td>
        </tr>
        <tr>
            <td>License date of issue</td>
            <td><input type="date" name="licenseDateOfIssue" /></td>
        </tr>
        <tr>
            <td>License validity period</td>
            <td><input type="text" name="licenseValidityPeriod" /></td>
        </tr>
        <tr>
            <td>Date and time start rent</td>
            <td><input type="datetime-local" name="date_time_start_rent" /></td>
        </tr>
        <tr>
            <td>Date and time finish rent</td>
            <td><input type="datetime-local" name="date_time_finish_rent" /></td>
        </tr>
    </table>
    <input type="submit" value="Check" /></form>
</body>
</html>
