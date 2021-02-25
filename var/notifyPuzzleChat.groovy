def call(String channel) {
    String result = currentBuild.result?.toLowerCase() ?: 'success'
    node {
        rocketSend(
                channel: channel,
                avatar: "https://chat.puzzle.ch/emoji-custom/${result}.png",
                message: "Build ${result} - ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)",
                rawMessage: true)
    }
}