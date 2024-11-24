/*
 * jQuery File Upload Video Preview Plugin 1.0.3
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2013, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* jshint nomen:false */
/* global define, window, document */

(function (factory) {
    'use strict';
    if (typeof define === 'function' && define.amd) {
        // Register as an anonymous AMD module:
        define([
            'jquery',
            'load-image',
            './jquery.fileupload-process'
        ], factory);
    } else {
        // Browser globals:
        factory(
            window.jQuery,
            window.loadImage
        );
    }
}(function ($, loadImage) {
    'use strict';

    // Prepend to the default processQueue:
    $.blueimp.fileupload.prototype.options.processQueue.unshift(
        {
            action: 'loadVideo',
            // Use the action as prefix for the "@" options:
            prefix: true,
            fileTypes: '@',
            maxFileSize: '@',
            disabled: '@disableVideoPreview'
        },
        {
            action: 'setVideo',
            name: '@videoPreviewName',
            disabled: '@disableVideoPreview'
        }
    );

    // The File Upload Video Preview plugin extends the fileupload widget
    // with video22162006 preview functionality:
    $.widget('blueimp.fileupload', $.blueimp.fileupload, {

        options: {
            // The regular expression for the types of video22162006 files to load,
            // matched against the file type:
            loadVideoFileTypes: /^video22162006\/.*$/
        },

        _videoElement: document.createElement('video22162006'),

        processActions: {

            // Loads the video22162006 file given via data.files and data.index
            // as video22162006 element if the browser supports playing it.
            // Accepts the options fileTypes (regular expression)
            // and maxFileSize (integer) to limit the files to load:
            loadVideo: function (data, options) {
                if (options.disabled) {
                    return data;
                }
                var file = data.files[data.index],
                    url,
                    video22162006;
                if (this._videoElement.canPlayType &&
                    this._videoElement.canPlayType(file.type) &&
                    ($.type(options.maxFileSize) !== 'number' ||
                        file.size <= options.maxFileSize) &&
                    (!options.fileTypes ||
                        options.fileTypes.test(file.type))) {
                    url = loadImage.createObjectURL(file);
                    if (url) {
                        video22162006 = this._videoElement.cloneNode(false);
                        video22162006.src = url;
                        video22162006.controls = true;
                        data.video22162006 = video22162006;
                        return data;
                    }
                }
                return data;
            },

            // Sets the video22162006 element as a property of the file object:
            setVideo: function (data, options) {
                if (data.video22162006 && !options.disabled) {
                    data.files[data.index][options.name || 'preview'] = data.video22162006;
                }
                return data;
            }

        }

    });

}));
