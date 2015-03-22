var SelectCategoryVM = function () {
    var self = this;
    self.activeCategory = ko.observable('select category');
    self.chooseBossFight = function () {
        self.activeCategory('Bossfight');
        $.get('/contribute/details/Bossfight', function (data) {
            $("#details").html(data);
        });
    };
    self.chooseClassSpec = function () {
        self.activeCategory('Class and Spec');
        $.get('/contribute/details/ClassSpec', function (data) {
            $("#details").html(data);
        });
    };
};

var model = new SelectCategoryVM();

var ActualInformationVM = function () {
    var self = this;
    self.uploadedTMWReferences = ko.observableArray([]);
    self.uploadedWAReferences = ko.observableArray([]);
    self.showTMW = ko.observable(false);
    self.showWA = ko.observable(true);
    self.showMacro = ko.observable(false);

    self.submitContribution = function () {

        $('#alertChooseOption').hide();
        $('#alertAddCaption').hide();
        $("#alertAddString").hide();
        $("#alertAddComments").hide();
        $("#alertChooseCategory").hide();

        var doPost = true;
        if ($("#selectWowClass")[0] === undefined) {
            $('#alertChooseOption').show();
            doPost = false;
        }


        var command = {};

        command['chooseOption'] = 'classspec';

        if (self.showTMW()) {
            if ($("#tmw_caption").val() === '') {
                $('#alertAddCaption').show();
                doPost = false;
            }

            if ($("#actual_tmw_string").val() === '') {
                $("#alertAddString").show();
                doPost = false;
            }

            if ($("#tmw_comments").val() === '') {
                $("#alertAddComments").show();
            }

            command['caption'] = $("#tmw_caption").val();
            command['category'] = 'TMW';
            command['actualValue'] = $('#actual_tmw_string').val();
            command['screenshots'] = self.uploadedTMWReferences();
            command['comments'] = $("#tmw_comments").val();
        } else if (self.showWA()) {
            if ($("#wa_caption").val() === '') {
                $('#alertAddCaption').show();
                doPost = false;
            }
            if ($("#actual_weakaura_string").val() === '') {
                $("#alertAddString").show();
                doPost = false;
            }
            if ($("#wa_comments").val() === '') {
                $("#alertAddComments").show();
            }

            command['category'] = 'WA';
            command['actualValue'] = $('#actual_weakaura_string').val();
            command['screenshots'] = self.uploadedWAReferences();
            command['caption'] = $("#wa_caption").val();
            command['comments'] = $("#wa_comments").val();
        } else if (self.showMacro()) {
            if ($("#macro_caption").val() === '') {
                $('#alertAddCaption').show();
                doPost = false;
            }

            if ($("#actual_macro_string").val() === '') {
                $("#alertAddString").show();
                doPost = false;
            }

            if ($("#macro_comments").val() === '') {
                $("#alertAddComments").show();
            }

            command['category'] = 'MACRO';
            command['actualValue'] = $('#actual_macro_string').val();
            command['caption'] = $("#macro_caption").val();
            command['comments'] = $("#macro_comments").val();
        } else {
            $("#alertChooseCategory").show();
            doPost = false;
        }

        command['wowClass'] = $('#selectWowClass').val();
        command['spec'] = $('#selectWowSpec').val();

        if (doPost) {
            var stringyfiedCommand = JSON.stringify(command);
            $.ajax({
                url: '/contribute/do-contribution',
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: stringyfiedCommand, //Stringified Json Object
                cache: false,    //This will force requested pages not to be cached by the browser
                processData: false, //To avoid making query String instead of JSON
                success: function (resposeJsonObject) {
                    window.location = "/?thankyou";
                }
            });
        }
    };
}

var actualInfoModel = new ActualInformationVM;

ko.applyBindings(model, $("#initialChoice")[0]);
ko.applyBindings(actualInfoModel, $("#actualInformation")[0]);

var setChosenType = function () {
    actualInfoModel.showTMW($("#optionTMW").is(':checked'));
    actualInfoModel.showWA($("#optionWA").is(':checked'));
    actualInfoModel.showMacro($("#optionMacro").is(':checked'));
};

$("#optionTMW").change(function () {
    setChosenType();
});

$("#optionWA").change(function () {
    setChosenType();
});

$("#optionMacro").change(function () {
    setChosenType();
});