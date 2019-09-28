job:
    name: 'cascade-choice-example'

    parameters:
      - string:
          name: STR_PARAM
          default: test
      - cascade-choice:
          project: 'cascade-choice-example'
          name: CASCADE_CHOICE
          script: |
            return ['foo:selected', 'bar']
          description: "A parameter named CASCADE_CHOICE which options foo and bar."
          visible-item-count: 1
          fallback-script: |
            return ['Something Wrong']
          reference: STR_PARAM
          choice-type: single
