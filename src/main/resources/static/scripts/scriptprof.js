const body = document.querySelector("body"),
      sidebar = body.querySelector("nav");
      sidebarToggle = body.querySelector(".sidebar-toggle");


let getStatus = localStorage.getItem("status");
    if(getStatus && getStatus ==="close"){
        sidebar.classList.toggle("close");
    }



sidebarToggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
    if(sidebar.classList.contains("close")){
        localStorage.setItem("status", "close");
    }else{
        localStorage.setItem("status", "open");
    }
})

const rowsPerPage = 6; // number of rows to show per page
    const rows = document.querySelectorAll('tbody tr');
    let currentPage = 1;

    function displayRows(start, end) {
        rows.forEach((row, index) => {
            row.style.display = (index >= start && index < end) ? '' : 'none';
        });
    }

    function setupPagination() {
        displayRows(0, rowsPerPage); // initially display first 'rowsPerPage' rows

        document.getElementById('prevBtn').addEventListener('click', () => {
            if (currentPage > 1) {
                currentPage--;
                displayRows((currentPage - 1) * rowsPerPage, currentPage * rowsPerPage);
            }
            updateButtons(); // Update buttons after changing the page
        });

        document.getElementById('nextBtn').addEventListener('click', () => {
            if ((currentPage * rowsPerPage) < rows.length) {
                currentPage++;
                displayRows((currentPage - 1) * rowsPerPage, currentPage * rowsPerPage);
            }
            updateButtons(); // Update buttons after changing the page
        });

        // Disable buttons if necessary
        updateButtons();
    }

    function updateButtons() {
        document.getElementById('prevBtn').disabled = (currentPage === 1);
        document.getElementById('nextBtn').disabled = ((currentPage * rowsPerPage) >= rows.length);
    }

    window.onload = setupPagination;

    


    let isSearchActive = false;

function searchTable() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toUpperCase();
    const tr = document.querySelectorAll('tbody tr');

    if (filter) {
        isSearchActive = true;
        tr.forEach((row) => {
            let td = row.getElementsByTagName('td')[1]; // Assuming the name is in the 3rd column (index 2)
            if (td) {
                let textValue = td.textContent || td.innerText;
                row.style.display = textValue.toUpperCase().indexOf(filter) > -1 ? "" : "none";
            }       
        });
    } else {
        isSearchActive = false;
        currentPage = 1; // Reset to first page
        displayRows(0, rowsPerPage); // Display first set of rows
        updateButtons(); // Update the state of pagination buttons
    }
}

document.getElementById('searchInput').addEventListener('keyup', searchTable);

function displayRows(start, end) {
    if (!isSearchActive) {
        rows.forEach((row, index) => {
            row.style.display = (index >= start && index < end) ? '' : 'none';
        });
    }
}

document.getElementById('openModalButton').addEventListener('click', function() {
    document.getElementById('studentModal').style.display = 'block';
});

function closeModal() {
    document.getElementById('studentModal').style.display = 'none';
}
    


