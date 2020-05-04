let publicationApi = Vue.resource('/publication{/id}');

Vue.http.headers.common['X-CSRF-TOKEN'] = csrf;

Vue.component('publication-form', {
    props: ['publications', 'publication'],
    data: function () {
        return {
            id: '',
            text: '',
            tag: '',
            fileName: '',
            // img: null
        }
    },
    watch: {
        publication: function (newVal, oldVal) {
            this.id = newVal.id;
            this.text = newVal.text;
            this.tag = newVal.tag;
            this.fileName = newVal.fileName;
            this.img = newVal.img;
        }
    },
    template:
        '<div>' +
        '<input type="text" v-model="text" placeholder="Что у вас нового?"/>' +
        '<input type="text" v-model="tag" placeholder="Тэг"/>' +
        '<input type="text" v-model="fileName" placeholder="fileName"/>' +
        '<input type="file" name="img" id="img" placeholder="Загрузка изображания" @change="downloadImg"/>' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        downloadImg(event) {
            let files = event.target.files || event.dataTransfer.files;
            if (!files.length)
                return;
            this.img = files[0];
        },
        save: function () {
            let publication = {
                text: this.text,
                tag: this.tag,
                img: this.img,
                fileName: this.fileName
            };

            if (this.id) {
                publicationApi.update({id: this.id}, publication).then(result =>
                    result.json().then(data => {
                        let index = this.publications.findIndex(item => item.id === data.id)
                        this.publications.splice(index, 1, data);
                        this.id = '';
                        this.text = '';
                    })
                );
            } else {
                publicationApi.save({}, publication).then(result =>
                    result.json().then(data => {
                        this.publications.push(data);
                        this.text = '';
                        this.tag = '';
                        this.fileName = '';
                        this.img = null;
                    })
                );
            }
        }
    }
})

Vue.component('publication-row', {
    props: ['publication', 'editMethod'],
    template:
        '<div>' +
        '<i>({{publication.id}})</i> {{publication.text}}' +
        '<span>' +
        '<input type="button" value="Edit" @click="edit">' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.publication);
        },
        del: function() {
            publicationApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.publications.splice(this.publications.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('publications-list', {
    props: ['publications'],
    data: function () {
        return {
            publication: null
        }
    },
    template:
        '<div>' +
        '<publication-form :publications="publications" :publication="publication"/>' +
        '<publication-row v-for="publication in publications" :key="publication.id" :publication="publication" :editMethod="editMethod"/>' +
        '</div>',
    created:
        function () {
            publicationApi.get().then(result =>
                result.json().then(data =>
                    data.forEach(publication => this.publications.push(publication))
                )
            )
        },
    methods: {
        editMethod: function (publication) {
            this.publication = publication;
        }
    }
});

let app = new Vue({
    el: '#app',
    template: '<publications-list :publications="publications"/>',
    data: {
        publications: []
    }
});