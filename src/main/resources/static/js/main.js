let publicationApi = Vue.resource('/id{user}/publication{/id}');

Vue.component('publication-row', {
    props: ['publication'],
    template: '<div><i>({{publication.id}})</i> {{publication.text}}</div>'
});

Vue.component('publications-list', {
    props: ['publications'],
    template: '<div><publication-row v-for="publication in publications" :key="publication.id" :publication="publication"/></div>',
    created: function () {
        publicationApi.get().then(result =>
            result.json().then(data =>
                data.forEach(publication => this.publications.push(publication))
            )
        )
    }
});

let app = new Vue({
    el: '#app',
    template: '<publications-list :publications="publications"/>',
    data: {
        publications: [
            {id: '1', text: 'test'},
            {id: '2', text: 'publication'}
        ]
    }
});