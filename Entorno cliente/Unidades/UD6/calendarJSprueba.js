function createCalendar() {

    let date = new Date();
    let actualMonth = date.getMonth();
    let actualYear = date.getFullYear();

    let firstDay = new Date(actualYear, actualMonth, 1);
    let lastDay = new Date(actualYear, actualMonth + 1, 0);

    let calendar = document.createElement('table');
    calendar.className = 'calendar';

    let rowHeader = document.createElement('tr');
    let daysWeek = ['L', 'M', 'X', 'J', 'V', 'S', 'D'];
    daysWeek.forEach(day => {
        let cellHeader = document.createElement('th');
        cellHeader.textContent = day;
        rowHeader.appendChild(cellHeader);
    });
    calendar.appendChild(rowHeader);

    //let numWeeks = Math.ceil((lastDay.getDay() + firstDay.getDay() / 7));

    let numWeeks = 5;
    let dayCont = 1;

    for (let i = 0; i < numWeeks ; i++) {
        let week = document.createElement('tr');

        for (let j = 1; j < 8; j++) {
            let dayCell = document.createElement('td');

            if (i === 0 && j < firstDay.getDay()) {
                dayCell.textContent = '';
            }
            else if (dayCont <= lastDay.getDate()) {
                dayCell.textContent = dayCont;
                dayCont++;
            }

            week.appendChild(dayCell);
        }

        calendar.appendChild(week);
    }

    document.body.appendChild(calendar);

}

createCalendar();