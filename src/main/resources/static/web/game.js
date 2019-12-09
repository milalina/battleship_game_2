new Vue({
    el: "#game",
    data: {
        games:[],
    },
    methods:{
        fetchData: function () {
            fetch("http://localhost:8080/api/game_view/1")
                .then(function (response) {
                    console.log(response);
                    return response.json();
                })
                .then((data) => {
                    console.log("bye")
                    console.log(data);
                    this.games = data;
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
    },
    created: function () {
        this.fetchData();
    },
})