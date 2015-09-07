var concat    = require('gulp-concat');
var es        = require('event-stream');
var gulp      = require('gulp');
var htmlmin   = require('gulp-htmlmin');
var less      = require('gulp-less');
var minifycss = require('gulp-minify-css');
var uglify    = require('gulp-uglify');
var argv      = require('yargs').argv;
var del       = require('del');
var jslint    = require('gulp-jslint-simple');

// Set variables so we can use them for vendor
// directories etc. Note the trailing slash
var SRC_DIR    = 'src/main/resources/frontend/';
var VENDOR_DIR = 'src/main/resources/vendor/';
var BUILD_DIR  = 'src/main/resources/public/';


// Copy index file to build directory
gulp.task('index', function(){
  return gulp.src(SRC_DIR + 'index.html')
         .pipe(gulp.dest(BUILD_DIR));
});


// Copy templates to the build directory
gulp.task('templates', function () {
  return gulp.src([
            SRC_DIR + '**/*.html',
            '!' + SRC_DIR + 'index.html'])
         .pipe(gulp.dest(BUILD_DIR + 'templates'));
});


// Concatenate Javascript files
gulp.task('js', function () {
  return gulp.src([
            SRC_DIR + 'main/main.js',
            SRC_DIR + '**/*.js',
            '!' + SRC_DIR + '**/*Test.js',
            '!' + SRC_DIR + '**/*Mock.js'])
         .pipe(concat('app.js'))
         .pipe(gulp.dest(BUILD_DIR + 'js'));
});


// Concatenate vendor files
gulp.task('js-vendor', function () {
  var vendor_scripts = [
    VENDOR_DIR + 'angular/angular.js',
    VENDOR_DIR + 'angular-route/angular-route.js',
    VENDOR_DIR + 'angular-mocks/angular-mocks.js', // Need to be removed for production
    VENDOR_DIR + 'angular-bootstrap/ui-bootstrap.js',
    VENDOR_DIR + 'angular-bootstrap/ui-bootstrap-tpls.js',
    VENDOR_DIR + 'moment/moment.js',
    VENDOR_DIR + 'angular-moment/angular-moment.js',
    VENDOR_DIR + 'angular-cookies/angular-cookies.js',
    VENDOR_DIR + 'ngInfiniteScroll/build/ng-infinite-scroll.js',
    VENDOR_DIR + 'angulartics/src/angulartics.js',
    VENDOR_DIR + 'angulartics/src/angulartics-ga.js'
  ];

  return gulp.src(vendor_scripts)
         .pipe(concat('vendor.js'))
         .pipe(gulp.dest(BUILD_DIR + 'js'));
});


// Copy vendor fonts
gulp.task('vendor-fonts', function() {
  return gulp.src(VENDOR_DIR + 'bootstrap-css-only/fonts/*')
      .pipe(gulp.dest(BUILD_DIR + 'fonts'));
});

// Copy font assets
gulp.task('fonts', function() {
    return gulp.src(SRC_DIR + 'fonts/**')
        .pipe(gulp.dest(BUILD_DIR + 'fonts'));
});

// Copy interstate fonts
gulp.task('interstate-fonts', function() {
    return gulp.src(SRC_DIR + 'fonts/interstate/*')
        .pipe(gulp.dest(BUILD_DIR + 'fonts/interstate'));
});

// Copy image assets
gulp.task('images', function() {
  return gulp.src(SRC_DIR + 'images/*')
         .pipe(gulp.dest(BUILD_DIR + 'images'));
});

// Compile less files
function lesscss() {
  return gulp.src(SRC_DIR + 'styles/main.less')
         .pipe(less());
}


// Get vendor CSS files
function vendorcss() {
  return gulp.src(VENDOR_DIR + 'bootstrap-css-only/css/bootstrap.css');
}


// Concatenate styles 
gulp.task('styles', function() {
  return es.merge(vendorcss(), lesscss())
         .pipe(concat('main.css'))
         .pipe(gulp.dest(BUILD_DIR + 'css'));
});


// Watch for file changes
gulp.task('watch', function() {
  // Watch for script changes and run the
  // javascript compile when any JS file
  // changes
  gulp.watch(SRC_DIR + '**/*.js', ['js']);

  // Update styles
  gulp.watch(SRC_DIR + '**/*.less', ['styles']);

  // Copy over templates
  gulp.watch(SRC_DIR + '**/*.html', ['index', 'templates']);

  // Watch for image changes
  gulp.watch(SRC_DIR + 'images/*', ['images']);

  // Watch for font changes
  gulp.watch(SRC_DIR + 'fonts/**', ['fonts']);
});


// Deletes the build directory
// This works best as it's own gulp task
gulp.task('clean', function () {
    del([BUILD_DIR]);
});


// Minification
gulp.task('compress', ['js', 'js-vendor'], function () {
    return gulp.src('src/main/resources/public/js/vendor.js')
        .pipe(uglify({outSourceMap: "src/main/resources/public/out.js.map"}))
        .pipe(gulp.dest('src/main/resources/public/js'));
});


// Linting
gulp.task('lint', function () {
    //gulp.src([SRC_DIR + '/**/*.js',
    //    '!' + SRC_DIR + '**/*Test.js',
    //    '!' + SRC_DIR + '**/*Mock.js'])
    gulp.src([SRC_DIR + '/**/*.js'])
        .pipe(jslint.run({
            // project-wide JSLint options
            node: true,
            vars: true
        }))
        .pipe(jslint.report({
            // example of using a JSHint reporter
            reporter: require('jshint-stylish').reporter
        }));
});


// Run all build steps
gulp.task('default', ['js', 'js-vendor', 'index', 'templates', 'styles', 'vendor-fonts', 'interstate-fonts', 'images', 'fonts', 'watch']);

// Run all build steps ONCE
gulp.task('cibuild', ['js', 'js-vendor', 'index', 'templates', 'styles', 'vendor-fonts', 'interstate-fonts', 'images', 'fonts','compress']);