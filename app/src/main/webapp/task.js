let url = "http://localhost:8080/tasks";
let data;

async function getData() {
    let res = await fetch(url);
    data = await res.json();
    return data;
};

function displayTasks(data, tableId) {
    for (let i = 0; i < data.length; i++) {
        let taskDiv = document.createElement("div");
        let taskRow = document.createElement("tr");
        taskDiv.appendChild(taskRow);

        if (tableId === "taskTableContent"){
            let idDiv = document.createElement("div");
            let id = document.createElement("td");
            id.textContent = data[i].id;
            taskRow.appendChild(id);
        }

        let date = document.createElement("td");
        date.textContent = new Date(data[i].date).toLocaleDateString();
        taskRow.appendChild(date);

        let type = document.createElement("td");
        type.textContent = data[i].taskType;
        taskRow.appendChild(type);

        let task = document.createElement("td");
        task.textContent = data[i].task;
        taskRow.appendChild(task);

        document.getElementById(tableId).appendChild(taskRow);
    }
}

async function start(){
    data = await getData();
    displayTasks(data[0],"taskTableContent");
    displayTasks(data[1],"completedTableContent");
}

start();