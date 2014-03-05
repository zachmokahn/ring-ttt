module.exports = (grunt) ->
  grunt.initConfig
    pkg: grunt.file.readJSON 'package.json'

    clean: ["public/static/js", "lib/js/"]

    jasmine:
      src: 'public/static/js/*.js'
      options:
        specs: 'lib/js/*.js'
        vendor: "public/static/vendor/*.js"
        helpers: 'public/static/support/*.js'

    coffee:
      specs:
        expand: true
        cwd:  'lib/spec'
        src:  '**/*.coffee'
        ext:  '.js'
        dest: 'lib/js'

      sources:
        expand: true
        cwd:  'lib/src'
        src:  '**/*.coffee'
        ext:  '.js'
        dest: 'public/static/js'

    watch:
      files: ['lib/src/*.coffee', 'lib/spec/*.coffee']
      tasks: 'spec'

  grunt.loadNpmTasks 'grunt-contrib-clean'
  grunt.loadNpmTasks 'grunt-contrib-jasmine'
  grunt.loadNpmTasks 'grunt-contrib-coffee'
  grunt.loadNpmTasks 'grunt-contrib-watch'

  grunt.registerTask 'cspec', ['clean', 'coffee', 'jasmine']
  grunt.registerTask 'spec', ['coffee', 'jasmine']
  grunt.registerTask 'default', 'cspec'
