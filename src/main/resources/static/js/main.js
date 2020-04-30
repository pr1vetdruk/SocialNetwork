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