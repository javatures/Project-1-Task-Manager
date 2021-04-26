let url = "http://localhost:8080/tasks";
let data;

async function getData() {
    let res = await fetch(url);
    data = await res.json();
    return data;
};

function displayTasks(data) {
    for (let i = 0; i < data.length; i++) {
        let taskDiv = document.createElement("div");
        let taskRow = document.createElement("tr");
        taskDiv.appendChild(taskRow);

        let idDiv = document.createElement("div");
        // let input = document.createElement("input");
        // input.type = "checkbox";
        // input.name = "id" + data[i].id;
        // idDiv.appendChild(input);
        let id = document.createElement("td");
        id.textContent = data[i].id;
        // idDiv.appendChild(id);
        taskRow.appendChild(id);

        let date = document.createElement("td");
        date.textContent = new Date(data[i].date).toLocaleDateString();
        taskRow.appendChild(date);

        let task = document.createElement("td");
        task.textContent = data[i].task;
        taskRow.appendChild(task);

        let type = document.createElement("td");
        type.textContent = data[i].taskType;
        taskRow.appendChild(type);

        document.getElementById("taskTableContent").appendChild(taskRow);
    }
}

async function start(){
    data = await getData();
    displayTasks(data);
}

start();