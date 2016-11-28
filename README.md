# SubtitleKitCLI
A series of small Java utilities to help with bilingual video subtitle productions.

A version of SubtitleKit with a graphical user interface [is available here](https://github.com/HiKay/SubtitleKitUI).

## Prerequisites:
- You need to install [JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html)
- If you are using Windows, you may also need to set up environmental variables
- If your subtitle is in one particular format and you would like to convert it to SRT or ASS, download [ffmpeg](https://ffmpeg.org/download.html) and run commands akin to the following:
- `ffmpeg -i [importedSubtitleFile.format] [exportedSubtitleFile.srt]`
- `ffmpeg -i [importedSubtitleFile.format] [exportedSubtitleFile.ass]`

## [SRTNormalizer](SRTNormalizer.java)
Converts SDH subtitles to standard subtitles by removing description of environmental sound and line breaks, generating one line of text during each timecode. 
Usage: 
- `java SRTNormalizer [importedSubtitleFile.srt] [normalizedSubtitleFile.srt]`
- `java SRTNormalizer [importedSubtitleFile.srt]`
- If unspecified, The normalized subtitle is exported as importedSubtitleFile_normalized.srt

## [SRTPrepare](SRTPrepare.java)
In addition to doing what [SRTNormalizer](SRTNormalizer.java) does, [SRTPrepare](SRTPrepare.java) also creates a new line above each transcription, making the SRT file ready for translations.
Usage: 
- `java SRTPrepare [importedSubtitleFile.srt] [preparedSubtitleFile.srt]`
- `java SRTPrepare [importedSubtitleFile.srt]`
- If unspecified, The subtitle is exported as importedSubtitleFile_normalized.srt

## [SRTTimeAlign](SRTTimeAlign.java)
Modifies SRT timecode so that the starting time of the next timecode duration aligns to the ending time of this timecode duration.
Usage: 
- `java SRTTimeAlign [importedSubtitleFile.srt] [exportedSubtitle.srt]`
- `java SRTTimeAlign [importedSubtitleFile.srt]`
- If unspecified, The subtitle is exported as importedSubtitleFile_aligned.srt

## [SRTLanFlip](SRTLanFlip.java)
Flips two lines of subtitle text within each timecode to allow easy formatting.
Usage: 
- `java SRTLanFlip [importedSubtitleFile.srt] [exportedSubtitle.srt]`
- `java SRTLanFlip [importedSubtitleFile.srt]`
- If unspecified, The subtitle is exported as importedSubtitleFile_flipped.srt

## [SRTToScript](SRTToScript.java)
Converts any SRT subtitle to plain text that only includes language, dialogue and voiceover transcription.
Usage: 
- `java SRTToScript [importedSubtitleFile.srt] [exportedSubtitle.txt]`
- `java SRTToScript [importedSubtitleFile.srt]`
- If unspecified, The subtitle is exported as importedSubtitleFile_text.txt

## [ASSStyle](ASSStyle.java)
Stylizes billingual AAS subtitle to have the first line in blue outline and larger font, with the second line in black outline and smaller font.
Usage: 
- If you would like to modify the typeface used for [ASSStyle](ASSStyle.java), mofity [ASSStyle](ASSStyle.java)'s source code and recompile.
Usage: 
- `java ASSStyle [importedSubtitleFile.ass] [exportedSubtitle.ass]`
- `java ASSStyle [importedSubtitleFile.ass]`
- If unspecified, The subtitle is exported as importedSubtitleFile_stylized.ass
