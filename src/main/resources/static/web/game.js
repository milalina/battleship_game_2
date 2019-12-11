new Vue({
    el: "#game",
    data: {
        game: [],
        col: ["", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
        row: ["", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        you: null,
        opponent: null,
        gamePlayerId: null,
        mySalvoes: [],
        opponentSalvoes: [],
    },
    methods: {
        fetchData: function () {
            fetch("http://localhost:8080/api/game_view/1")
                .then(function (response) {
                    console.log(response);
                    return response.json();
                })
                .then((data) => {
                    console.log(data);
                    this.game = data;
                    this.getParamsFromUrl();
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        createShipGrid: function () {
            var table = document.getElementById("shipGrid");
            table.innerHTML = "";
            var tbl = document.createElement("table");
            var tblBody = document.createElement("tbody");
            for (i in this.row) {
                var row = document.createElement("tr");
                for (j in this.col) {
                    var cell = document.createElement("td");
                    cell.className = "cell"
                    cell.id = "" + this.row[i] + this.col[j];
                    row.appendChild(cell);
                    if (this.col[j] == "") {
                        var cellText = document.createTextNode(this.row[i] + "")
                        cell.appendChild(cellText)
                    }
                    if (this.row[i] == "") {
                        var cellText = document.createTextNode(this.col[j] + "")
                        cell.appendChild(cellText)
                    }
                }
                tblBody.appendChild(row);
            }
            tbl.appendChild(tblBody)
            table.appendChild(tbl)
            tbl.setAttribute("border", "2");

            for (l in this.game.ships) {
                for (k in this.game.ships[l].locations) {
                    var shipCell = document.getElementById(this.game.ships[l].locations[k] + "")
                    shipCell.style = "background-color:#92A8D1"
                }
            }
        },
        createSalvoGrid: function () {
            var table = document.getElementById("salvoGrid");
            table.innerHTML = "";
            var tbl = document.createElement("table");
            var tblBody = document.createElement("tbody");
            for (i in this.row) {
                var row = document.createElement("tr");
                for (j in this.col) {
                    var cell = document.createElement("td");
                    cell.className = "cell"
                    cell.id = "s" + this.row[i] + this.col[j];
                    row.appendChild(cell);
                    if (this.col[j] == "") {
                        var cellText = document.createTextNode("" + this.row[i])
                        cell.appendChild(cellText)
                    }
                    if (this.row[i] == "") {
                        var cellText = document.createTextNode("" + this.col[j])
                        cell.appendChild(cellText)
                    }
                }
                tblBody.appendChild(row);
            }
            tbl.appendChild(tblBody)
            table.appendChild(tbl)
            tbl.setAttribute("border", "2");
            console.log(this.game.gamePlayers[0].salvoes)
            for (l in this.mySalvoes) {
                console.log("hello")
                for (c in this.mySalvoes[l].salvoes) {
                    console.log("hello")
                    var salvoCell = document.getElementById("s" + this.mySalvoes[l].salvoes[c])
                    salvoCell.style = "background-color:#92A8D1"
                }

            }
        },
        getParamsFromUrl() {
            var parsedUrl = new URL(window.location.href);
            this.gamePlayerId = (parsedUrl.searchParams.get("gp"));
            if (this.gamePlayerId == this.game.gamePlayers[0].id) {
                this.you = this.game.gamePlayers[0].player.email
                this.opponent = this.game.gamePlayers[1].player.email
                this.mySalvoes = this.game.gamePlayers[0].salvoes
                this.opponentSalvoes = this.game.gamePlayers[1].salvoes
            } else {
                this.you = this.game.gamePlayers[1].player.email
                this.opponent = this.game.gamePlayers[0].player.email
                this.mySalvoes = this.game.gamePlayers[1].salvoes
                this.opponentSalvoes = this.game.gamePlayers[0].salvoes
            }
            this.createShipGrid();
            this.createSalvoGrid();
        }
    },
    created: function () {
        this.fetchData();
    },
})